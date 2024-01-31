package com.kosign.phone_shop_api.repository;

import com.kosign.phone_shop_api.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModelRepository extends JpaRepository<Model, Integer> {
    List<Model> findModelsByBrandId(Integer categoryId);
}
