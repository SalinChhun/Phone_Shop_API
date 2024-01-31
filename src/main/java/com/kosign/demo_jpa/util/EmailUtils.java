package com.kosign.demo_jpa.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailUtils {

    public static boolean isEmail(String input) {
        // Define a regex pattern for a simple email address (change as needed)
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(input);

        return matcher.matches();
    }
}
