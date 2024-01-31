package com.kosign.demo_jpa.repository;

import com.kosign.demo_jpa.entity.ProductImportHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductImportRepository extends JpaRepository<ProductImportHistory, Integer>, JpaSpecificationExecutor<ProductImportHistory> {
}
