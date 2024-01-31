package com.kosign.demo_jpa.payload.product;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ProductResponse {
    private Integer productId;
    private String productName;
    private Integer availableUnit;
    private BigDecimal salePrice;
    private String imagePath;
    private String modelName;
    private String colorName;

    @Builder
    public ProductResponse(Integer productId, String productName, Integer availableUnit, BigDecimal salePrice, String imagePath, String modelName, String colorName) {
        this.productId = productId;
        this.productName = productName;
        this.availableUnit = availableUnit;
        this.salePrice = salePrice;
        this.imagePath = imagePath;
        this.modelName = modelName;
        this.colorName = colorName;
    }
}
