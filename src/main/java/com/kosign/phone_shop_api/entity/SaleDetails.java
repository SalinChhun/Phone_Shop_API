package com.kosign.phone_shop_api.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Entity
@NoArgsConstructor
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

    @Builder
    public SaleDetails(Integer id,
                       Product productId,
                       Sale saleId,
                       BigDecimal amount,
                       Integer unit
    ) {
        this.id = id;
        this.productId = productId;
        this.saleId = saleId;
        this.amount = amount;
        this.unit = unit;
    }
}
