package com.kosign.phone_shop_api.repository;

import com.kosign.phone_shop_api.entity.SaleDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SaleDetailsRepository extends JpaRepository<SaleDetails, Integer> {

    List<SaleDetails> findBySaleIdId(Integer id);
}
