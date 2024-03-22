package com.kosign.phone_shop_api.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "product_image_tb")
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    private String photo;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Builder
    public ProductImage(Integer id, String photo, Product product) {
        this.id = id;
        this.photo = photo;
        this.product = product;
    }
}
