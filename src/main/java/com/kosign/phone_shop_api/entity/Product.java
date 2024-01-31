package com.kosign.phone_shop_api.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "product_tb", uniqueConstraints = {@UniqueConstraint(columnNames = {"model_id","color_id"})})
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "product_name", unique = true)
    private String productName;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private List<ProductImage> images;

    @Column(name = "available_unit")
    private Integer availableUnit;

    @ManyToOne
    @JoinColumn(name = "model_id")
    private  Model  modelId;

    @ManyToOne
    @JoinColumn(name = "color_id")
    private Color colorId;

    @Column(name = "sale_price")
    private BigDecimal salePrice;

    @Builder
    public Product(Integer id, String productName, List<ProductImage> images, Integer availableUnit, Model modelId, Color colorId, BigDecimal salePrice) {
        this.id = id;
        this.productName = productName;
        this.images = images;
        this.availableUnit = availableUnit;
        this.modelId = modelId;
        this.colorId = colorId;
        this.salePrice = salePrice;
    }
}
