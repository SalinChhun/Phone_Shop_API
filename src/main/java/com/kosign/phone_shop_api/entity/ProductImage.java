package com.kosign.phone_shop_api.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    private String photo;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

}
