package com.kosign.demo_jpa.service;

import com.kosign.demo_jpa.payload.report.ExpenseReportRequest;
import com.kosign.demo_jpa.projection.ProductSold;

import java.time.LocalDate;
import java.util.List;

public interface ReportService {

    List<ProductSold> getProductSold(LocalDate startDate, LocalDate endDate);
    List<ExpenseReportRequest> getExpenseReport(LocalDate startDate, LocalDate endDate);
}
