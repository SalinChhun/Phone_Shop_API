package com.kosign.phone_shop_api.projection;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;

public interface ProductSold {

    Integer getProductId();

    String getProductName();

    Integer getUnit();

    BigDecimal getTotalAmount();
}
