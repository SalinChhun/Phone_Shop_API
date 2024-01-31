package com.kosign.phone_shop_api.spec;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ProductImportHistoryFilter {
    private LocalDate startDate;
    private LocalDate endDate;
}
