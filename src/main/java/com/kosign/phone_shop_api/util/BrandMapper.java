package com.kosign.phone_shop_api.util;

import com.kosign.phone_shop_api.dto.BrandDTO;
import com.kosign.phone_shop_api.entity.Brand;

public class BrandMapper {
    public static Brand toBrand(BrandDTO brandDTO){
        Brand brand = new Brand();
        brand.setBrandName(brandDTO.getName());
        return brand;
    }
}
