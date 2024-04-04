package com.kosign.phone_shop_api.service.brand;

import com.kosign.phone_shop_api.entity.brand.Brand;
import org.springframework.data.domain.Pageable;

public interface BrandService {

    Brand createNewBrand(Brand brand);

    Object getAllBrand(Pageable pageable) throws Throwable;

    Brand getBrandById(Integer id);
}
