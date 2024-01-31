package com.kosign.demo_jpa.repository;

import com.kosign.demo_jpa.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {
    Optional<Product> findByModelIdIdAndColorIdId(Integer modelId, Integer colorId);

    @Query("select p from Product p order by p.id DESC")
    Page<Product> findByOrderByIdDesc(Pageable pageable);

}
