package com.kosign.demo_jpa.payload.brand;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.kosign.demo_jpa.common.api.Pagination;
import com.kosign.demo_jpa.entity.Brand;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@NoArgsConstructor
@JsonPropertyOrder({"brands", "pagination"})
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BrandResponse {

    List<Brand> brands;
    private Pagination pagination;
    @Builder
    public BrandResponse(List<Brand> brands, Page<?> page) {
        this.brands = brands;
        this.pagination = new Pagination(page);
    }

}
