package com.kosign.phone_shop_api.service.product;

import com.kosign.phone_shop_api.entity.product.Product;
import com.kosign.phone_shop_api.payload.product.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface ProductService {

    void createNewProduct(ProductRequest product);

    void createMultiProducts(ProductsRequest products);
//    Object getAllProduct(Pageable pageable);
    Object getAllProduct(ProductCriteria criteria);

    Product getProductById(Integer id);

    Product getByModelIdAndColorId(Integer modelId, Integer colorId);

    void importProduct(ImportProductRequest importProductRequest);

    Map<Integer, String> uploadProduct(MultipartFile multipartFile);

    void setSalePrice(Integer productId, PriceRequest price) throws Throwable;
}
