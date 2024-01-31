package com.kosign.phone_shop_api.controller;

import com.kosign.phone_shop_api.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/reports/")
public class ReportController {

    private final ReportService reportService;

    @GetMapping("getAllProductSold/{startDate}/{endDate}")
    ResponseEntity<?> getAllProductSold(
            @PathVariable LocalDate startDate,
            @PathVariable LocalDate endDate
    ) {
        return ResponseEntity.ok(reportService.getProductSold(startDate, endDate));
    }

    @GetMapping("expense/{startDate}/{endDate}")
    ResponseEntity<?> expenseReport(
            @PathVariable LocalDate startDate,
            @PathVariable LocalDate endDate
    ) {

        return ResponseEntity.ok(reportService.getExpenseReport(startDate, endDate));
    }


}
