package com.kosign.phone_shop_api.service;

import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductImageService {

    public void createProductImage(List<String> imageUrl, Long productId);
}
