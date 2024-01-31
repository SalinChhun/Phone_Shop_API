package com.kosign.phone_shop_api.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
public class ProductImportHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "date_import")
    private LocalDate dateImport;

    @Column(name = "import_unit")
    private Integer importUnit;

    @Column(name = "price_per_unit")
    private BigDecimal pricePerUnit;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product productId;

    @Builder
    public ProductImportHistory(Integer id, LocalDate dateImport, Integer importUnit, BigDecimal pricePerUnit, Product productId) {
        this.id = id;
        this.dateImport = dateImport;
        this.importUnit = importUnit;
        this.pricePerUnit = pricePerUnit;
        this.productId = productId;
    }
}
