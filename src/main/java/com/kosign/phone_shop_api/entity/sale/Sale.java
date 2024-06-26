package com.kosign.phone_shop_api.entity.sale;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "sale_tb")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "sold_date")
    private LocalDate soldDate;

    private Boolean status;
}
