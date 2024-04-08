package com.kosign.phone_shop_api.payload.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductCriteria {

    private String sortColumns;
    private Integer pageNumber;
    private Integer pageSize;
    private LocalDate startDate;
    private LocalDate endDate;
    private String searchValue;

}
