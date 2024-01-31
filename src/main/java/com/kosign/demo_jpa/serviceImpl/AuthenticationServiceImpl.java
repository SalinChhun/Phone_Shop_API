package com.kosign.demo_jpa.serviceImpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kosign.demo_jpa.common.api.StatusCode;
import com.kosign.demo_jpa.entity.User;
import com.kosign.demo_jpa.exception.BusinessException;
import com.kosign.demo_jpa.payload.auth.LoginRequest;
import com.kosign.demo_jpa.payload.auth.LoginResponse;
import com.kosign.demo_jpa.payload.auth.RegisterRequest;
import com.kosign.demo_jpa.payload.auth.ResetPasswordRequest;
import com.kosign.demo_jpa.repository.UserRepository;
import com.kosign.demo_jpa.security.config.JwtService;
import com.kosign.demo_jpa.security.token.Token;
import com.kosign.demo_jpa.security.token.TokenRepository;
import com.kosign.demo_jpa.security.token.TokenType;
import com.kosign.demo_jpa.service.AuthenticationService;
import com.kosign.demo_jpa.util.EmailUtils;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository repository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public LoginResponse register(RegisterRequest request) {

        // Check before register
        if (request.getEmail() == null || StringUtils.isBlank(request.getEmail())) {
            throw new BusinessException(StatusCode.EMAIL_IS_NUll);
        } else if (!EmailUtils.isEmail(request.getEmail())) {
            throw new BusinessException(StatusCode.INVALID_EMAIL_FORMAT);
        } else if (repository.findByEmail(request.getEmail()).isPresent()) {
            throw new BusinessException(StatusCode.EMAIL_EXIST);
        }

        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();
        var savedUser = repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        saveUserToken(savedUser, jwtToken);
        return LoginResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public LoginResponse login(LoginRequest request) {

        var user = repository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BusinessException(StatusCode.EMAIL_NOT_FOUND));

        // login validation
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        }  catch (BadCredentialsException ex) {
            // Handle incorrect password
            throw new BusinessException(StatusCode.BAD_CREDENTIALS);
        }
        catch (AuthenticationException ex) {
            // Handle other authentication exceptions
            throw new BusinessException(StatusCode.AUTHENTICATION_FAILED, ex.getMessage());
        }


        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return LoginResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    @Override
    public void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    @Override
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }

        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);

        if (userEmail != null) {
            var user = this.repository.findByEmail(userEmail).orElseThrow();

            if (jwtService.isTokenValid(refreshToken, user)) {
                try {
                    var accessToken = jwtService.generateToken(user);
                    revokeAllUserTokens(user);
                    saveUserToken(user, accessToken);

                    var authResponse = LoginResponse.builder()
                            .accessToken(accessToken)
                            .refreshToken(refreshToken)
                            .build();

                    new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
                } catch (ExpiredJwtException e) {
                    // Handle the case where the new access token cannot be generated due to the expiration of the refresh token.
                    // You may want to return an error response to the client.
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Refresh token has expired. Please re-authenticate.");
                }
            } else {
                // Handle the case where the refresh token is not valid (e.g., tampered or expired).
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid refresh token. Please re-authenticate.");
            }
        }
    }


}
