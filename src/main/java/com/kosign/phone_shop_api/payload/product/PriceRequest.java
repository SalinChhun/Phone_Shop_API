package com.kosign.phone_shop_api.payload.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

@Data
@JsonInclude
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@NoArgsConstructor
public class PriceRequest {

    @DecimalMin(value = "0.000001", message = "Price must be greater than 0!")
    @NotNull
    private BigDecimal salePrice;

    @Builder
    public PriceRequest(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }
}
