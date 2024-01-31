package com.kosign.demo_jpa.serviceImpl;

import com.kosign.demo_jpa.common.api.StatusCode;
import com.kosign.demo_jpa.entity.Product;
import com.kosign.demo_jpa.entity.Sale;
import com.kosign.demo_jpa.entity.SaleDetails;
import com.kosign.demo_jpa.exception.BusinessException;
import com.kosign.demo_jpa.exception.EntityNotFoundException;
import com.kosign.demo_jpa.payload.sale.ProductSoldRequest;
import com.kosign.demo_jpa.payload.sale.SaleRequest;
import com.kosign.demo_jpa.repository.ProductRepository;
import com.kosign.demo_jpa.repository.SaleDetailsRepository;
import com.kosign.demo_jpa.repository.SaleRepository;
import com.kosign.demo_jpa.service.ProductService;
import com.kosign.demo_jpa.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final SaleDetailsRepository saleDetailsRepository;
    private final ProductRepository productRepository;
    private final ProductService productService;


    @Override
    public void sale(SaleRequest saleRequest) {

        //validation
        List<Integer> productId = saleRequest.getProduct().stream()
                .map(ProductSoldRequest::getProductId)
                .toList();
        //product validation
        productId.forEach(productService::getProductById);
        List<Product> products = productRepository.findAllById(productId);
        Map<Integer, Product> productMap = products.stream()
                .collect(Collectors.toMap(Product::getId, Function.identity()));

        //stock validation
        saleRequest.getProduct()
                .forEach(ps -> {
                    Product product = productMap.get(ps.getProductId());
                    if(product.getAvailableUnit() < ps.getNumberOfUnit()) {
                        throw new BusinessException(StatusCode.PRODUCT_OUT_OF_STOCK);
                    }
                });

        //save sale
        Sale sale = new Sale();
        sale.setSoldDate(saleRequest.getSaleDate());
        saleRepository.save(sale);

        //save sale details
        saleRequest.getProduct()
                .forEach(ps -> {
                    Product product = productMap.get(ps.getProductId());
                    SaleDetails saleDetails = new SaleDetails();
                    saleDetails.setAmount(product.getSalePrice());
                    saleDetails.setProductId(product);
                    saleDetails.setSaleId(sale);
                    saleDetails.setUnit(ps.getNumberOfUnit());
                    saleDetailsRepository.save(saleDetails);

                    //cut stock
                    Integer availableUnit = product.getAvailableUnit() - ps.getNumberOfUnit();
                    product.setAvailableUnit(availableUnit);
                    productRepository.save(product);
                });


    }

    @Override
    public Sale getSaleById(Integer id) {
        return saleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Sale.class, "id", id.toString()));
    }

    @Override
    public void cancelSale(Integer id) {

        //update sale status
        Sale sale = getSaleById(id);
        sale.setStatus(false);
        saleRepository.save(sale);

        //update stock
        List<SaleDetails> saleDetails = saleDetailsRepository.findBySaleIdId(id);
        List<Integer> productId = saleDetails.stream()
                .map(sd -> sd.getProductId().getId())
                .toList();
        List<Product> products = productRepository.findAllById(productId);
        Map<Integer, Product> productMap = products.stream()
                .collect(Collectors.toMap(Product::getId, Function.identity()));

        saleDetails.forEach(sd -> {
            Product product = productMap.get(sd.getProductId().getId());
            product.setAvailableUnit(product.getAvailableUnit() + sd.getUnit());
            productRepository.save(product);
        });
    }

//    private void validate(SaleRequest saleRequest) {
//        saleRequest.getProduct().forEach(ps -> {
//            Product product = productService.getProductById(ps.getProductId());
//            if(product.getAvailableUnit() < ps.getNumberOfUnit()) {
//                throw new ApiException(HttpStatus.BAD_REQUEST, "Product with the %s is not enough in stock!");
//            }
//        });
//    }
//
//    private void saveSale(SaleRequest saleRequest) {
//        Sale sale = new Sale();
//        sale.setSoldDate(saleRequest.getSaleDate());
//        saleRepository.save(sale);
//
//        //sale details
//        saleRequest.getProduct().forEach(ps -> {
//            SaleDetails saleDetails = new SaleDetails();
//            saleDetails.setAmount(null);
//        });
//    }
}
