package com.kosign.demo_jpa.service;

import com.kosign.demo_jpa.entity.Product;
import com.kosign.demo_jpa.payload.product.ImportProductRequest;
import com.kosign.demo_jpa.payload.product.PriceRequest;
import com.kosign.demo_jpa.payload.product.ProductRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface ProductService {

    void createNewProduct(ProductRequest product);

//    List<ProductResponse> getAllProduct();

    Object getAllProduct(Pageable pageable);

    Product getProductById(Integer id);

    Product getByModelIdAndColorId(Integer modelId, Integer colorId);

    void importProduct(ImportProductRequest importProductRequest);

    Map<Integer, String> uploadProduct(MultipartFile multipartFile);

    void setSalePrice(Integer productId, PriceRequest price) throws Throwable;
}
