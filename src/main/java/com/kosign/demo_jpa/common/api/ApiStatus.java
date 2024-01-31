package com.kosign.demo_jpa.common.api;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApiStatus {

    private int code;
    private String message;

    public ApiStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ApiStatus(StatusCode statusCode) {
        this.code = statusCode.getCode();
        this.message = statusCode.getMessage();
    }
}
