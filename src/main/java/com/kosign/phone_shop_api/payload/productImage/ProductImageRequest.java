package com.kosign.phone_shop_api.payload.productImage;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonInclude
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ProductImageRequest {
    private String photo;

}
