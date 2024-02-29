package com.kosign.phone_shop_api.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "model_tb")
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String modelName;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    Brand brand;

    @Builder
    public Model(Integer id, String modelName, Brand brand) {
        this.id = id;
        this.modelName = modelName;
        this.brand = brand;
    }
}
