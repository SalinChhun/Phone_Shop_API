package com.kosign.demo_jpa.controller;

import com.kosign.demo_jpa.entity.Product;
import com.kosign.demo_jpa.payload.MultiSortBuilder;
import com.kosign.demo_jpa.payload.product.ImportProductRequest;
import com.kosign.demo_jpa.payload.product.PriceRequest;
import com.kosign.demo_jpa.payload.product.ProductRequest;
import com.kosign.demo_jpa.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/product/")
public class ProductController extends PhoneShopResController{

    private final ProductService productService;
    @PostMapping("createNewProduct")
    ResponseEntity<?> createNewProduct(@RequestBody ProductRequest productRequest) {
        productService.createNewProduct(productRequest);
        return ok();
    }

    @GetMapping("getAllProducts")
    ResponseEntity<?> getAllProduct(
            @RequestParam(name = "page_number", defaultValue = "0") Integer pageNumber,
            @RequestParam(name = "page_size", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "sort_column", required = false, defaultValue = "id:desc") String sortColumns
    ) {
        List<Sort.Order> sortBuilder = new MultiSortBuilder().with(sortColumns).build();
        Pageable pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by(sortBuilder));
        var product = productService.getAllProduct(pageRequest);
        return ok(product);
    }

    @GetMapping("{id}")
    ResponseEntity<?> getProductById(@PathVariable Integer id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PostMapping("importProduct")
    ResponseEntity<?> importProduct(@RequestBody @Valid ImportProductRequest importProductRequest) {
        productService.importProduct(importProductRequest);
        return ok();
    }

    @PostMapping("uploadProduct")
    ResponseEntity<?> uploadProduct(@RequestParam("file")MultipartFile file) {
        Map<Integer, String> errorMessage = productService.uploadProduct(file);
        return ResponseEntity.ok(errorMessage);
    }

    @PostMapping("setSalePrice/{productId}")
    ResponseEntity<?> setSalePrice(@PathVariable Integer productId, @RequestBody @Valid PriceRequest priceRequest) throws Throwable {
        productService.setSalePrice(productId, priceRequest);
        return ok();
    }
}
