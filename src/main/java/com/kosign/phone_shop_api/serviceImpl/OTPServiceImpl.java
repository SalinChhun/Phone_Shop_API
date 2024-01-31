package com.kosign.phone_shop_api.serviceImpl;

import com.kosign.phone_shop_api.common.api.StatusCode;
import com.kosign.phone_shop_api.entity.User;
import com.kosign.phone_shop_api.exception.BusinessException;
import com.kosign.phone_shop_api.payload.pincode.PinCodeRequest;
import com.kosign.phone_shop_api.repository.UserRepository;
import com.kosign.phone_shop_api.service.OTPService;
import com.kosign.phone_shop_api.service.SendMailOTPService;
import com.kosign.phone_shop_api.util.ConfirmPinCodeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Random;

@RequiredArgsConstructor
@Service
public class OTPServiceImpl implements OTPService {

    private final SendMailOTPService sendMailOTPService;
    private final UserRepository userRepository;

    @Override
    public PinCodeRequest generatePinCode(String email) {

        Random random  = new Random();
        int pinCode = random.nextInt(900000)+100000;
        String code = String.valueOf(pinCode);
        PinCodeRequest pinCodeRequest = new PinCodeRequest();
        pinCodeRequest.setEmail(email);
        pinCodeRequest.setPinCode(code);

        User user = userRepository.findByEmail(email).orElseThrow(() -> new BusinessException(StatusCode.EMAIL_NOT_FOUND));
        user.setPinCodeGenerateTime(LocalDateTime.now());
        user.setPinCode(code);
        userRepository.save(user);

        sendMailOTPService.sendMailOTP(pinCodeRequest);

        return pinCodeRequest;
    }

    @Override
    public String confirmPinCode(String email, String pinCode) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new BusinessException(StatusCode.EMAIL_NOT_FOUND));
        if (user.getPinCode().equals(pinCode) && Duration.between(user.getPinCodeGenerateTime(), LocalDateTime.now()).getSeconds() < 60) {
            user.setIsPinCodeEnable(true);
            userRepository.save(user);
            return "PIN code verified successfully";
        } else if (!user.getPinCode().equals(pinCode)) {
            throw new BusinessException(StatusCode.INVALID_PINCODE);
        } else if (!ConfirmPinCodeUtil.IsConfirmPinCode(pinCode)) {
            throw new BusinessException(StatusCode.INVALID_PINCODE_FORMAT);
        } else if (!(Duration.between(user.getPinCodeGenerateTime(), LocalDateTime.now()).getSeconds() < 60)) {
            throw new BusinessException(StatusCode.EXPIRED_PINCODE);
        }
        return null;
    }

}
