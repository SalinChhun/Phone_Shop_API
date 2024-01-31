package com.kosign.demo_jpa.service;

import com.kosign.demo_jpa.entity.Sale;
import com.kosign.demo_jpa.payload.sale.SaleRequest;

public interface SaleService {
   void sale (SaleRequest saleRequest);

   Sale getSaleById(Integer id);

   void cancelSale(Integer id);

}
