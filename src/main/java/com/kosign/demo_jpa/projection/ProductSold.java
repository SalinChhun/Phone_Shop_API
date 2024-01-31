package com.kosign.demo_jpa.projection;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;

public interface ProductSold {

    Integer getProductId();

    String getProductName();

    Integer getUnit();

    BigDecimal getTotalAmount();
}
