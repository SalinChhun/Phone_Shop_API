package com.kosign.phone_shop.controller;

import com.kosign.phone_shop.dto.BrandDTO;
import com.kosign.phone_shop.dto.PageDTO;
import com.kosign.phone_shop.entity.Brand;
import com.kosign.phone_shop.mapper.BrandMapper;
import com.kosign.phone_shop.service.BrandService;
import com.kosign.phone_shop.service.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @PostMapping("createNewBrand")
    public ResponseEntity<?> createNewBrand(@RequestBody BrandDTO brandDTO) {

//        without use mapstruct
//        Brand brand = Mapper.toBrand(brandDTO);

//        using mapstruct
        Brand brand = BrandMapper.INSTANT.toBrand(brandDTO); //using mapstruct
        brand = brandService.create(brand);
        return ResponseEntity.ok(brand);
    }

    @GetMapping("getAllBrand")
    public ResponseEntity<?> getAllBrand() {

        return ResponseEntity.ok(brandService.getAllBrands());

//        using stream
//        List<BrandDTO> list = brandService.getAllBrands()
//                .stream()
//                .map(BrandMapper.INSTANT::toBrandDTO)
//                .collect(Collectors.toList());
//        return ResponseEntity.ok(list);
    }



//    method 1
//    public ResponseEntity<?> getBrandById(@PathVariable("id") Integer brandId) {
//        return null;
//    }

//    method 2
    @GetMapping("{id}")
    public ResponseEntity<?> getBrandById(@PathVariable Integer id) {
        Brand brand = brandService.getBrandById(id);
        return ResponseEntity.ok(brand);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateBrandById(@PathVariable Integer id, @RequestBody BrandDTO brandDTO) {
        Brand brand = Mapper.toBrand(brandDTO);
        Brand updatedBrand = brandService.updateBrand(id, brand);
        return ResponseEntity.ok(updatedBrand);
    }

//    normal search
    @GetMapping("searchBrandByName")
    public ResponseEntity<?> searchBrandByName(@RequestParam("name") String name) {

        List<BrandDTO> list = brandService.searchBrandByName(name)
                .stream()
                .map(BrandMapper.INSTANT::toBrandDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

//    using map
    @GetMapping("searchBrandByNameUsingMap")
    public ResponseEntity<?> searchBrandByName(@RequestParam Map<String, String> param) {

        List<BrandDTO> list = brandService.searchBrandUsingMap(param)
                .stream()
                .map(BrandMapper.INSTANT::toBrandDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    //    using pagination
    @GetMapping("getAllBrandWithPagination")
    public ResponseEntity<?> searchBrandByNameWithPagination(@RequestParam Map<String, String> param) {
        Page<Brand> pages = brandService.searchBrandWithPagination(param);
        PageDTO pageDTO = new PageDTO(pages);
        return ResponseEntity.ok(pageDTO);
    }


}
