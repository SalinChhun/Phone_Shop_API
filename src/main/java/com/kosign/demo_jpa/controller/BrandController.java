package com.kosign.demo_jpa.controller;

import com.kosign.demo_jpa.dto.BrandDTO;
import com.kosign.demo_jpa.entity.Brand;
import com.kosign.demo_jpa.entity.Model;
import com.kosign.demo_jpa.payload.MultiSortBuilder;
import com.kosign.demo_jpa.service.BrandService;
import com.kosign.demo_jpa.service.ModelService;
import com.kosign.demo_jpa.util.BrandMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/brand/")
public class BrandController extends PhoneShopResController{

    private final BrandService brandService;
    private final ModelService modelService;

    @PostMapping("createNewBrand")
    public ResponseEntity<?> createNewBrand(@RequestBody BrandDTO brandDTO) {
        Brand brand = BrandMapper.toBrand(brandDTO);
        brandService.createNewBrand(brand);
        return ResponseEntity.ok(brand);
    }

    @GetMapping("getAllBrand")
    public ResponseEntity<?> getAllBrand(
            @RequestParam(name = "page_number", defaultValue = "0") Integer pageNumber,
            @RequestParam(name = "page_size", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "sort_columns", required = false, defaultValue = "id:desc") String sortColumns
    ) throws Throwable {
        List<Sort.Order> sortBuilder = new MultiSortBuilder().with(sortColumns).build();
        Pageable pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by(sortBuilder));
        return ok(brandService.getAllBrand(pageRequest));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getBrandById(@PathVariable Integer id) {
        return ok(brandService.getBrandById(id));
    }

    @GetMapping("{id}/models")
    public ResponseEntity<?> getAllProductByCategoryId(@PathVariable Integer id) {
        List<Model> models = modelService.getAllModelByBrandId(id);
        return ResponseEntity.ok(models);
    }

}
