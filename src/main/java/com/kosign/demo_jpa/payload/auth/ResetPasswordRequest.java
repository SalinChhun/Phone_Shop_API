package com.kosign.demo_jpa.payload.auth;

import lombok.Data;

@Data
public class ResetPasswordRequest {

    private String newPassword;
    private String confirmNewPassword;
}
