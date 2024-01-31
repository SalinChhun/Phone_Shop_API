package com.kosign.demo_jpa.repository;

import com.kosign.demo_jpa.entity.SaleDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SaleDetailsRepository extends JpaRepository<SaleDetails, Integer> {

    List<SaleDetails> findBySaleIdId(Integer id);
}
