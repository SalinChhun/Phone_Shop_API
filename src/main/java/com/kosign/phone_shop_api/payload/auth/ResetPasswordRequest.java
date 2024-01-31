package com.kosign.phone_shop_api.payload.auth;

import lombok.Data;

@Data
public class ResetPasswordRequest {

    private String newPassword;
    private String confirmNewPassword;
}
