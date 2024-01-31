package com.kosign.demo_jpa.util;

import com.kosign.demo_jpa.common.api.StatusCode;
import com.kosign.demo_jpa.exception.BusinessException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConfirmPinCodeUtil {

    public static boolean IsConfirmPinCode(String InputPinCode){
        String pinCode = "^[0-9]{6}$";
        Pattern pattern = Pattern.compile(pinCode);
        Matcher matcher = pattern.matcher(InputPinCode);
        return matcher.matches();
    }
}
