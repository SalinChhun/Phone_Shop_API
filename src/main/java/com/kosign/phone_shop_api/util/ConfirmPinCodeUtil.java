package com.kosign.phone_shop_api.util;

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
