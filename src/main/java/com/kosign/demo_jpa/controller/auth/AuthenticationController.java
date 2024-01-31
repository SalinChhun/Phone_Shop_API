package com.kosign.demo_jpa.controller.auth;

import com.kosign.demo_jpa.controller.PhoneShopResController;
import com.kosign.demo_jpa.payload.auth.LoginRequest;
import com.kosign.demo_jpa.payload.auth.LoginResponse;
import com.kosign.demo_jpa.payload.auth.RegisterRequest;
import com.kosign.demo_jpa.service.AuthenticationService;
import com.kosign.demo_jpa.service.OTPService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController extends PhoneShopResController {

    private final AuthenticationService authenticationService;
    private final OTPService otpService;

    @PostMapping("/register")
    public ResponseEntity<LoginResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(authenticationService.register(request));
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(
            @RequestBody LoginRequest request
    ) {
        return ResponseEntity.ok(authenticationService.login(request));
    }

    @PostMapping("/refreshToken")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        authenticationService.refreshToken(request, response);
    }

    @PostMapping("/generatePinCode")
    public ResponseEntity<?> generatePinCode(@RequestParam String email){
        return ok(otpService.generatePinCode(email));
    }

    @PostMapping("/confirmPinCode")
    public ResponseEntity<?> confirmPinCode(
            @RequestParam String email,
            @RequestParam String pinCode
    ) {
        return ok(otpService.confirmPinCode(email, pinCode));
    }

}
