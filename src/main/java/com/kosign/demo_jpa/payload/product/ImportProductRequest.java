package com.kosign.demo_jpa.payload.product;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@JsonInclude
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ImportProductRequest {

    @NotNull(message = "category_id cannot be null!")
    private Integer productId;

    @Min(value = 1, message = "import unit must be greater than 0!")
    private Integer importUnit;

    private BigDecimal importPrice;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Import date can't be null!")
    private LocalDate importDate;

    @Builder
    public ImportProductRequest(Integer productId, Integer importUnit, BigDecimal importPrice, LocalDate importDate) {
        this.productId = productId;
        this.importUnit = importUnit;
        this.importPrice = importPrice;
        this.importDate = importDate;
    }
}
