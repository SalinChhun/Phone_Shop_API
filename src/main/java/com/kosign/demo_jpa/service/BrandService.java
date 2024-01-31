package com.kosign.demo_jpa.service;

import com.kosign.demo_jpa.entity.Brand;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BrandService {

    Brand createNewBrand(Brand brand);

    Object getAllBrand(Pageable pageable) throws Throwable;

    Brand getBrandById(Integer id);
}
