package com.kosign.demo_jpa.controller;

import com.kosign.demo_jpa.payload.sale.SaleRequest;
import com.kosign.demo_jpa.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/sales/")
public class SaleController extends PhoneShopResController{

    private final SaleService saleService;

    @PostMapping("sale")
    ResponseEntity<?> sale(@RequestBody SaleRequest saleRequest) {
        saleService.sale(saleRequest);
        return ok();
    }

    @PutMapping("cancelSale/{saleId}")
    ResponseEntity<?> cancelSale(@PathVariable Integer saleId) {
        saleService.cancelSale(saleId);
        return ok();
    }
}
