package com.kosign.phone_shop_api.repository;

import com.kosign.phone_shop_api.entity.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface BrandRepository extends JpaRepository<Brand, Integer> {

    @Query("select b from Brand b order by b.id DESC")
    Page<Brand> findByOrderByIdDesc(Pageable pageable);
}
