package com.kosign.phone_shop_api.entity.productHistory;

import com.kosign.phone_shop_api.entity.productHistory.ProductImportHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductImportRepository extends JpaRepository<ProductImportHistory, Integer>, JpaSpecificationExecutor<ProductImportHistory> {
}
