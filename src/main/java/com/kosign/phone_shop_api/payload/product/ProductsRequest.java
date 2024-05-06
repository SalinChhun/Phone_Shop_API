package com.kosign.phone_shop_api.payload.product;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ProductsRequest {

    List<ProductRequest> productRequests;

    @Builder
    public ProductsRequest(List<ProductRequest> productRequests) {
        this.productRequests = productRequests;
    }
}
