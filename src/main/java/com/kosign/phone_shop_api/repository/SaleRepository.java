package com.kosign.phone_shop_api.repository;

import com.kosign.phone_shop_api.entity.Sale;
import com.kosign.phone_shop_api.projection.ProductSold;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Integer> {

    @Query(value = """
                SELECT p.id productId, p.product_name productName, SUM(sd.unit) unit, SUM(sd.unit * sd.amount) totalAmount, s.sold_date
                FROM sale_details sd
                LEFT JOIN sale s ON sd.sale_id = s.id
                LEFT JOIN product p ON p.id = sd.product_id
                WHERE s.status = ?1
                GROUP BY p.id, p.product_name, s.sold_date;
            """, nativeQuery = true)
    List<ProductSold> findProductSold(Boolean saleStatus);


}
