package com.kosign.demo_jpa.service;

import com.kosign.demo_jpa.payload.pincode.PinCodeRequest;

public interface OTPService {
    PinCodeRequest generatePinCode(String email);

    String confirmPinCode(String email, String pinCode);

}
