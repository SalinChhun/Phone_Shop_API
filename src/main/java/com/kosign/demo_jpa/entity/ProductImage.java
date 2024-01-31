package com.kosign.demo_jpa.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

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
