package com.kosign.demo_jpa.util;

import com.kosign.demo_jpa.dto.BrandDTO;
import com.kosign.demo_jpa.entity.Brand;

public class BrandMapper {
    public static Brand toBrand(BrandDTO brandDTO){
        Brand brand = new Brand();
        brand.setBrandName(brandDTO.getName());
        return brand;
    }
}
