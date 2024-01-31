package com.kosign.phone_shop_api.service;

import com.kosign.phone_shop_api.entity.Brand;
import org.springframework.data.domain.Pageable;

public interface BrandService {

    Brand createNewBrand(Brand brand);

    Object getAllBrand(Pageable pageable) throws Throwable;

    Brand getBrandById(Integer id);
}
