package com.kosign.phone_shop_api.service.notification;

public interface NotificationService {

    public void pushNotification(Long receiverAccount, String message);
}
