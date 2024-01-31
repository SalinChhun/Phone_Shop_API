package com.kosign.phone_shop_api.payload.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;

@Data
@JsonInclude
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ProductRequest {

    private Integer modelId;
    private Integer colorId;

    @Builder
    public ProductRequest(Integer modelId, Integer colorId) {
        this.modelId = modelId;
        this.colorId = colorId;
    }
}
