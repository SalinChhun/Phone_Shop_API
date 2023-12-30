package com.kosign.phone_shop.serviceImpl;

import com.kosign.phone_shop.entity.Brand;
import com.kosign.phone_shop.exeption.ApiException;
import com.kosign.phone_shop.exeption.ResourceNotFoundException;
import com.kosign.phone_shop.repository.BrandRepository;
import com.kosign.phone_shop.service.BrandService;
import com.kosign.phone_shop.specification.BrandFilter;
import com.kosign.phone_shop.specification.BrandSpec;
import com.kosign.phone_shop.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandRepository brandRepository;

    @Override
    public Brand create(Brand brand) {
        return brandRepository.save(brand);
    }

    @Override
    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    @Override
    public Brand getBrandById(Integer id) {

//        method 1
//        Optional<Brand> brandOptional = brandRepository.findById(id);
//        if(brandOptional.isPresent()) {
//            return brandOptional.get();
//        }
//        throw new HttpClientErrorException(HttpStatus.NOT_FOUND, String.format("Brand with id = %d id not found!", id));

//        method 2
        return brandRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Brand", id));
    }

    @Override
    public Brand updateBrand(Integer id, Brand brandUpdate) {
        Brand brand = getBrandById(id);
        brand.setName(brandUpdate.getName());
        return brandRepository.save(brand);
    }

    @Override
    public List<Brand> searchBrandByName(String name) {
        return brandRepository.findByNameContaining(name);
    }

    @Override
    public List<Brand> searchBrandUsingMap(Map<String, String> params) {
        BrandFilter brandFilter = new BrandFilter();

        if (params.containsKey("name")) {
            String name = params.get("name");
            brandFilter.setName(name);
        }

        if (params.containsKey("id")) {
            String id = params.get("id");
            brandFilter.setId(Integer.parseInt(id));
        }

        BrandSpec brandSpec = new BrandSpec(brandFilter);
        return brandRepository.findAll(brandSpec);
    }

    @Override
    public Page<Brand> searchBrandWithPagination(Map<String, String> params) {
        BrandFilter brandFilter = new BrandFilter();
        int pageLimit = PageUtil.DEFAULT_PAGE_LIMIT;
        if (params.containsKey(PageUtil.PAGE_LIMIT)) {
            pageLimit = Integer.parseInt(params.get(PageUtil.PAGE_LIMIT));
        }

        int pageNumber = PageUtil.DEFAULT_PAGE_NUMBER;
        if (params.containsKey(PageUtil.PAGE_NUMBER)) {
            pageNumber = Integer.parseInt(params.get(PageUtil.PAGE_NUMBER));
        }

        Pageable pageable = PageUtil.getPageable(pageNumber,pageLimit);

        BrandSpec brandSpec = new BrandSpec(brandFilter);

        Page<Brand> page = brandRepository.findAll(brandSpec, pageable);
        return page;
    }


}
