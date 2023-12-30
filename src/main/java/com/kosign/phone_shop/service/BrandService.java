package com.kosign.phone_shop.service;

import com.kosign.phone_shop.dto.BrandDTO;
import com.kosign.phone_shop.entity.Brand;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface BrandService {
    Brand create(Brand brand);

    List<Brand> getAllBrands();

    Brand getBrandById(Integer id);

    Brand updateBrand(Integer id, Brand brandUpdate);

//    normal search
    List<Brand> searchBrandByName(String name);

//    search using map
    List<Brand> searchBrandUsingMap(Map<String, String> params);

    Page<Brand> searchBrandWithPagination(Map<String, String> params);

}
