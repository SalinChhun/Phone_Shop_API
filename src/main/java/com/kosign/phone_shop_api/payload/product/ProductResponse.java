package com.kosign.phone_shop_api.payload.product;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.kosign.phone_shop_api.entity.ProductImage;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ProductResponse {
    private Integer productId;
    private String productName;
    private Integer availableUnit;
    private BigDecimal salePrice;
    private List<ProductImageResponse> productImages;
    private String modelName;
    private String colorName;

    @Builder
    public ProductResponse(Integer productId, String productName, Integer availableUnit, BigDecimal salePrice, List<ProductImageResponse> productImages, String modelName, String colorName) {
        this.productId = productId;
        this.productName = productName;
        this.availableUnit = availableUnit;
        this.salePrice = salePrice;
        this.productImages = productImages;
        this.modelName = modelName;
        this.colorName = colorName;
    }
}
