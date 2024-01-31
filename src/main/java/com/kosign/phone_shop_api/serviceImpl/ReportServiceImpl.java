package com.kosign.phone_shop_api.serviceImpl;

import com.kosign.phone_shop_api.entity.Product;
import com.kosign.phone_shop_api.entity.ProductImportHistory;
import com.kosign.phone_shop_api.payload.report.ExpenseReportRequest;
import com.kosign.phone_shop_api.projection.ProductSold;
import com.kosign.phone_shop_api.repository.ProductImportRepository;
import com.kosign.phone_shop_api.repository.ProductRepository;
import com.kosign.phone_shop_api.repository.SaleRepository;
import com.kosign.phone_shop_api.service.ReportService;
import com.kosign.phone_shop_api.spec.ProductImportHistoryFilter;
import com.kosign.phone_shop_api.spec.ProductImportHistorySpec;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final SaleRepository saleRepository;
    private final ProductRepository productRepository;
    private final ProductImportRepository productImportRepository;

    @Override
    public List<ProductSold> getProductSold(LocalDate startDate, LocalDate endDate) {
        return saleRepository.findProductSold(startDate, endDate);
    }

    @Override
    public List<ExpenseReportRequest> getExpenseReport(LocalDate startDate, LocalDate endDate) {
        ProductImportHistoryFilter productImportHistoryFilter = new ProductImportHistoryFilter();
        productImportHistoryFilter.setStartDate(startDate);
        productImportHistoryFilter.setEndDate(endDate);

        ProductImportHistorySpec spec = new ProductImportHistorySpec(productImportHistoryFilter);
        List<ProductImportHistory> importHistories = productImportRepository.findAll(spec);

        Set<Integer> productId = importHistories.stream()
                .map(his -> his.getProductId().getId())
                .collect(Collectors.toSet());

        Map<Product, List<ProductImportHistory>> importMap = importHistories.stream()
                .collect(Collectors.groupingBy(pi -> pi.getProductId()));

        List<Product> products = productRepository.findAllById(productId);
        Map<Integer, Product> productMap = products.stream().collect(Collectors.toMap(p -> p.getId(), p->p));

        List<ExpenseReportRequest> expenseReportResponse = new ArrayList<>();

        for (var entry: importMap.entrySet()) {
            Product product = productMap.get(entry.getKey().getId());

            List<ProductImportHistory> importProduct = entry.getValue();

            int totalUnit = importProduct.stream()
                    .mapToInt(pi -> pi.getImportUnit())
                    .sum();

            double totalAmount = importProduct.stream()
                    .mapToDouble(pi -> pi.getImportUnit() * pi.getPricePerUnit().doubleValue())
                    .sum();

            var expenseReportRequests = new ExpenseReportRequest();
            expenseReportRequests.setProductId(product.getId());
            expenseReportRequests.setProductName(product.getProductName());
            expenseReportRequests.setTotalUnit(totalUnit);
            expenseReportRequests.setTotalAmount(BigDecimal.valueOf(totalAmount));
            expenseReportResponse.add(expenseReportRequests);
        }
        return expenseReportResponse;
    }
}