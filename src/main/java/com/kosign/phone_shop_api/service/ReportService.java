package com.kosign.phone_shop_api.service;

import com.kosign.phone_shop_api.payload.report.ExpenseReportRequest;
import com.kosign.phone_shop_api.projection.ProductSold;

import java.time.LocalDate;
import java.util.List;

public interface ReportService {

    List<ProductSold> getProductSold(LocalDate startDate, LocalDate endDate);
    List<ExpenseReportRequest> getExpenseReport(LocalDate startDate, LocalDate endDate);
}
