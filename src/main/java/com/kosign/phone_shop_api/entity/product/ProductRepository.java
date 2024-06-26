package com.kosign.phone_shop_api.entity.product;

import com.kosign.phone_shop_api.entity.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {
    Optional<Product> findByModelIdIdAndColorIdId(Integer modelId, Integer colorId);

    @Query("select p from Product p order by p.id DESC")
    Page<Product> findByOrderByIdDesc(Pageable pageable);

    Optional<Product> findByModelId_IdAndColorId_Id(Integer modelId, Integer colorId);

//    @Override
//    Page<Product> findAll(Specification<Product> spec, Pageable pageable);

}
