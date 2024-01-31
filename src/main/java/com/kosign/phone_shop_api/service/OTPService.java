package com.kosign.phone_shop_api.service;

import com.kosign.phone_shop_api.payload.pincode.PinCodeRequest;

public interface OTPService {
    PinCodeRequest generatePinCode(String email);

    String confirmPinCode(String email, String pinCode);

}
