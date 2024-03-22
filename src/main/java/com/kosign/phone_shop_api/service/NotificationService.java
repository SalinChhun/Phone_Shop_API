package com.kosign.phone_shop_api.service;

public interface NotificationService {

    public void pushNotification(Long receiverAccount, String message);
}
