package com.kosign.phone_shop_api.service.otp;

import com.kosign.phone_shop_api.payload.pincode.PinCodeRequest;

public interface SendMailOTPService {

    public void sendMailOTP(PinCodeRequest pinCodeRequest);
}
