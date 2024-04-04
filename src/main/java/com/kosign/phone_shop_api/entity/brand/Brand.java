package com.kosign.phone_shop_api.entity.brand;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "brand_tb")
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String brandName;

    @Builder
    public Brand(Integer id, String brandName) {
        this.id = id;
        this.brandName = brandName;
    }
}
