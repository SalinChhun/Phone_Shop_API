package com.kosign.phone_shop_api.payload.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.kosign.phone_shop_api.entity.ProductImage;
import com.kosign.phone_shop_api.payload.productImage.ProductImageRequest;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ProductRequest {

    private Integer modelId;
    private Integer colorId;
    private List<ProductImageRequest> productImage;

    @Builder
    public ProductRequest(Integer modelId, Integer colorId, List<ProductImageRequest> productImage) {
        this.modelId = modelId;
        this.colorId = colorId;
        this.productImage = productImage;
    }
}
