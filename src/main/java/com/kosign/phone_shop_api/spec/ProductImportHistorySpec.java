package com.kosign.phone_shop_api.spec;

import com.kosign.phone_shop_api.entity.ProductImportHistory;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
public class ProductImportHistorySpec implements Specification<ProductImportHistory> {

    private ProductImportHistoryFilter productImportHistoryFilter;

    @Override
    public Predicate toPredicate(Root<ProductImportHistory> productImportHistory, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();
        if(Objects.nonNull(productImportHistoryFilter.getStartDate())) {
            cb.greaterThanOrEqualTo(productImportHistory.get("dateImport"), productImportHistoryFilter.getStartDate());
        }
        if(Objects.nonNull(productImportHistoryFilter.getEndDate())) {
            cb.lessThanOrEqualTo(productImportHistory.get("dateImport"), productImportHistoryFilter.getEndDate());
        }
        Predicate predicate = cb.and(predicates.toArray(Predicate[]::new));
        return predicate;
    }
}
