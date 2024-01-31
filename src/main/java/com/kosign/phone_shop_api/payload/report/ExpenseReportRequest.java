package com.kosign.phone_shop_api.payload.report;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ExpenseReportRequest {

    private Integer productId;
    private String productName;
    private Integer totalUnit;
    private BigDecimal totalAmount;
}
