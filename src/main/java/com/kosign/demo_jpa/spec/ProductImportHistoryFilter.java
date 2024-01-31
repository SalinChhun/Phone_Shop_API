package com.kosign.demo_jpa.spec;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ProductImportHistoryFilter {
    private LocalDate startDate;
    private LocalDate endDate;
}
