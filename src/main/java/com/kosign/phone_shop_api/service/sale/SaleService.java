package com.kosign.phone_shop_api.service.sale;

import com.kosign.phone_shop_api.entity.sale.Sale;
import com.kosign.phone_shop_api.payload.sale.SaleRequest;

public interface SaleService {
   void sale (SaleRequest saleRequest);

   Sale getSaleById(Integer id);

   void cancelSale(Integer id);

}
