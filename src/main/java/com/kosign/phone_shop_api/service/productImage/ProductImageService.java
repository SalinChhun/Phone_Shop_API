package com.kosign.phone_shop_api.service.productImage;

import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductImageService {

    public void createProductImage(List<String> imageUrl, Long productId);
}
