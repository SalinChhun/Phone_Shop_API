package com.kosign.phone_shop_api.repository;

import com.kosign.phone_shop_api.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
}
