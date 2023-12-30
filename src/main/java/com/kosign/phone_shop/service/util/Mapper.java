package com.kosign.phone_shop.service.util;

import com.kosign.phone_shop.dto.BrandDTO;
import com.kosign.phone_shop.entity.Brand;

public class Mapper {
    public static Brand toBrand(BrandDTO dto) {
        Brand brand = new Brand();
//        brand.setId(dto.getId());
        brand.setName(dto.getName());
        return brand;
    }

    public static Brand toBrandDTO(BrandDTO dto) {
        Brand brand = new Brand();
        brand.setName(dto.getName());
        return brand;
    }
}
