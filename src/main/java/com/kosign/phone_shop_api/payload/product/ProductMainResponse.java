package com.kosign.phone_shop_api.payload.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.kosign.phone_shop_api.common.api.Pagination;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@JsonInclude
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonPropertyOrder({"products", "pagination"})
public class ProductMainResponse {

    private Pagination pagination;
    private List<ProductResponse> products;

    @Builder
    public ProductMainResponse(List<ProductResponse> products, Page<?> page) {
        this.products = products;
        this.pagination = new Pagination(page);
    }
}
