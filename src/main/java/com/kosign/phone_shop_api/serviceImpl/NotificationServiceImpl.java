package com.kosign.phone_shop_api.serviceImpl;

import com.kosign.phone_shop_api.service.NotificationService;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

@Service
public class NotificationServiceImpl implements NotificationService {
    @Override
    public void pushNotification(Long receiverAccount, String message) {

        try {
            String jsonResponse;
            URL url = new URL("https://onesignal.com/api/v1/notifications");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setUseCaches(false);
            con.setDoOutput(true);
            con.setDoInput(true);

            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            con.setRequestProperty("Authorization", "Basic NDM4MjcxZGMtYTUwNy00OTM5LWE0ODMtODlkMDdlMTY4M2E0");
            con.setRequestMethod("POST");

            String strJsonBody = "{"
                    + "\"app_id\": \"b786cc0b-6c78-4625-9729-54b0676cc056\","
                    + "\"include_external_user_ids\": [\"" + receiverAccount + "\"],"
                    + "\"channel_for_external_user_ids\": \"push\","
                    + "\"data\": {\"foo\": \"bar\"},"
                    + "\"contents\": {\"en\": \"" + message + "\"}"
                    + "}";
//            System.out.println("strJsonBody:\n" + strJsonBody);

            byte[] sendBytes = strJsonBody.getBytes("UTF-8");
            con.setFixedLengthStreamingMode(sendBytes.length);

            OutputStream outputStream = con.getOutputStream();
            outputStream.write(sendBytes);

            int httpResponse = con.getResponseCode();
//            System.out.println("httpResponse: " + httpResponse);

            if (httpResponse >= HttpURLConnection.HTTP_OK
                    && httpResponse < HttpURLConnection.HTTP_BAD_REQUEST) {
                Scanner scanner = new Scanner(con.getInputStream(), "UTF-8");
                jsonResponse = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
                scanner.close();
            } else {
                Scanner scanner = new Scanner(con.getErrorStream(), "UTF-8");
                jsonResponse = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
                scanner.close();
            }
//            System.out.println("jsonResponse:\n" + jsonResponse);

        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
