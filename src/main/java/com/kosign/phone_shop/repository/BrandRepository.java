package com.kosign.phone_shop.repository;

import com.kosign.phone_shop.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer>, JpaSpecificationExecutor<Brand> {

//    search for specific name
    List<Brand> findByNameLike(String name);

//    search for ignore case
//    List<Brand> findByNameIgnoreCase(String name);

    List<Brand> findByNameContaining(String name);
}
