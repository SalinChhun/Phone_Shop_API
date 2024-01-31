package com.kosign.demo_jpa.serviceImpl;

import com.kosign.demo_jpa.entity.Brand;
import com.kosign.demo_jpa.exception.EntityNotFoundException;
import com.kosign.demo_jpa.payload.brand.BrandResponse;
import com.kosign.demo_jpa.repository.BrandRepository;
import com.kosign.demo_jpa.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandRepository brandRepository;
    @Override
    public Brand createNewBrand(Brand brand) {
        return brandRepository.save(brand);
    }

    @Override
    public Object getAllBrand(Pageable pageable) throws Throwable{
        Page<Brand> allBrand = brandRepository.findByOrderByIdDesc(pageable);
        var  response = allBrand.stream()
//        List<Brand> response = allBrand.stream()
                .map(brand -> Brand.builder()
                        .id(brand.getId())
                        .brandName(brand.getBrandName())
                        .build()).toList();
        return BrandResponse.builder()
                .brands(response)
                .page(allBrand)
                .build();
    }

    @Override
    public Brand getBrandById(Integer id) {
        return brandRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Brand.class, "id", id.toString()));
    }

}
