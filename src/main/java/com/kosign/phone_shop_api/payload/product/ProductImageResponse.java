package com.kosign.phone_shop_api.payload.product;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ProductImageResponse {
    private Integer id;
    private String image_url;

    @Builder
    public ProductImageResponse(Integer id, String image_url) {
        this.id = id;
        this.image_url = image_url;
    }
}
