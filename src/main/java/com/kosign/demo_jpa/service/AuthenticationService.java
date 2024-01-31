package com.kosign.demo_jpa.service;

import com.kosign.demo_jpa.entity.User;
import com.kosign.demo_jpa.payload.auth.LoginRequest;
import com.kosign.demo_jpa.payload.auth.LoginResponse;
import com.kosign.demo_jpa.payload.auth.RegisterRequest;
import com.kosign.demo_jpa.payload.auth.ResetPasswordRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface AuthenticationService {
    public LoginResponse register(RegisterRequest request);

    public LoginResponse login(LoginRequest request);

    void saveUserToken(User user, String jwtToken);

    void revokeAllUserTokens(User user);

    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException;


}
