package com.kosign.demo_jpa.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
public class SaleDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product productId;

    @ManyToOne
    @JoinColumn(name = "sale_id")
    private Sale saleId;

    private BigDecimal amount;

    private Integer unit;
}
