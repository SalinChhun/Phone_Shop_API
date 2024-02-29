package com.kosign.phone_shop_api.service;

import com.kosign.phone_shop_api.payload.report.ExpenseReportRequest;
import com.kosign.phone_shop_api.payload.report.ProductSoldCriteria;
import com.kosign.phone_shop_api.projection.ProductSold;

import java.time.LocalDate;
import java.util.List;

public interface ReportService {

    List<ProductSold> getProductSold(Boolean saleDetail);

    Object getProductSold(ProductSoldCriteria criteria);
    List<ExpenseReportRequest> getExpenseReport(LocalDate startDate, LocalDate endDate);
}
