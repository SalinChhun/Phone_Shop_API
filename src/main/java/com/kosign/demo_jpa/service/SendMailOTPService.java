package com.kosign.demo_jpa.service;

import com.kosign.demo_jpa.payload.pincode.PinCodeRequest;

public interface SendMailOTPService {

    public void sendMailOTP(PinCodeRequest pinCodeRequest);
}
