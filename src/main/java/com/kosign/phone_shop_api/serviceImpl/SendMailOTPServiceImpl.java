package com.kosign.phone_shop_api.serviceImpl;

import com.kosign.phone_shop_api.payload.pincode.PinCodeRequest;
import com.kosign.phone_shop_api.service.SendMailOTPService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SendMailOTPServiceImpl implements SendMailOTPService {
    private final Logger logger = LoggerFactory.getLogger(SendMailOTPServiceImpl.class);
    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;
    @Override
    public void sendMailOTP(PinCodeRequest pinCodeRequest) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(sender);
            mailMessage.setTo(pinCodeRequest.getEmail());
            mailMessage.setText("Dear Sir/Madam ,"+"\n\n"+"The Verification Code is "+pinCodeRequest.getPinCode()+"\n\n"+"From Phone Shop Service");
            mailMessage.setSubject("Phone Shop Service");
            javaMailSender.send(mailMessage);
        } catch (Exception e) {
            logger.error("Error sending mail", e);
        }
    }
}
