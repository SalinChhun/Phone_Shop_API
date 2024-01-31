package com.kosign.demo_jpa.repository;

import com.kosign.demo_jpa.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModelRepository extends JpaRepository<Model, Integer> {
    List<Model> findModelsByBrandId(Integer categoryId);
}
