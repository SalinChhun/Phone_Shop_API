package com.kosign.phone_shop_api.controller;

import com.kosign.phone_shop_api.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/reports/")
public class ReportController {

    private final ReportService reportService;

    @GetMapping("getAllProductSold")
    ResponseEntity<?> getAllProductSold(
           @RequestParam(name = "sale_status", required = false, defaultValue = "true") Boolean saleStatus
    ) {
        return ResponseEntity.ok(reportService.getProductSold(saleStatus));
    }

    @GetMapping("expense/{startDate}/{endDate}")
    ResponseEntity<?> expenseReport(
            @PathVariable LocalDate startDate,
            @PathVariable LocalDate endDate
    ) {

        return ResponseEntity.ok(reportService.getExpenseReport(startDate, endDate));
    }


}
