package com.kosign.demo_jpa.common.api;

public enum StatusCode {
    SUCCESS(200, "Success"),
    FORBIDDEN(403, "You are not authorized to perform this action"),
    NOT_FOUND(404, "Not Found"),
    BAD_REQUEST(400, "Bad Request"),
    USER_NOT_FOUND(453, "User is not found"),
    EMAIL_NOT_FOUND(453, "Email is not found"),
    INVALID_EMAIL_FORMAT(453, "Invalid email format"),
    BAD_CREDENTIALS(452, "Password is incorrect"),
    VERIFY_PASSWORD(452, "Confirm password must be the same with new password"),
    EMAIL_EXIST(409, "Email is already exist"),
    EMAIL_IS_NUll(404, "Email is null"),
    AUTHENTICATION_FAILED(453, "Authentication failed"),
    UNAUTHORIZED(401, "Unauthorized"),
    PRODUCT_OUT_OF_STOCK(404, "This product is not available in stock"),
    IMPORT_UNIT_MUST_GREATER_THAN_ZERO(404, "must greater than 0"),
    INVALID_PINCODE_FORMAT(404, "Pin code must be input only number 6 digit"),
    INVALID_PINCODE(404, "Invalid pin code"),
    EXPIRED_PINCODE(404, "PIN code has expired."),
    PINCODE_REQUIRED(404, "You need to verify pin code first");

    private final String message;
    private final int code;

    StatusCode(final int code, final String message) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {

        return this.message;

    }

    public int getCode() {

        return code;

    }
}
