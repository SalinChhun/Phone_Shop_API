package com.kosign.phone_shop_api.service;

import com.kosign.phone_shop_api.entity.User;
import com.kosign.phone_shop_api.payload.auth.LoginRequest;
import com.kosign.phone_shop_api.payload.auth.LoginResponse;
import com.kosign.phone_shop_api.payload.auth.RegisterRequest;
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
