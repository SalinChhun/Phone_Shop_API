package com.kosign.phone_shop_api.serviceImpl;

import com.kosign.phone_shop_api.common.api.StatusCode;
import com.kosign.phone_shop_api.entity.*;
import com.kosign.phone_shop_api.entity.Color;
import com.kosign.phone_shop_api.exception.BusinessException;
import com.kosign.phone_shop_api.exception.EntityNotFoundException;
import com.kosign.phone_shop_api.payload.product.*;
import com.kosign.phone_shop_api.payload.productImage.ProductImageRequest;
import com.kosign.phone_shop_api.repository.ProductImageRepository;
import com.kosign.phone_shop_api.repository.ProductImportRepository;
import com.kosign.phone_shop_api.repository.ProductRepository;
import com.kosign.phone_shop_api.service.*;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelService modelService;
    private final ColorService colorService;
    private final ProductImportRepository productImportRepository;
    private final NotificationService notificationService;
    private final ProductImageRepository productImageRepository;


    @Override
    public void createNewProduct(ProductRequest productRequest) {

        Model model = modelService.getModelById(productRequest.getModelId());
        Color color = colorService.getColorById(productRequest.getColorId());


        var checkProduct = productRepository.findByModelId_IdAndColorId_Id(productRequest.getModelId(), productRequest.getColorId());
        if (checkProduct.isPresent()) {
            throw new BusinessException(StatusCode.PRODUCT_ALREADY_EXIST);
        }

        // create product
        Product product = Product.builder()
                .productName(model.getModelName() + " " + color.getColorName())
                .modelId(model)
                .colorId(color)
                .build();
        productRepository.save(product);

//        save product image
        var productPhoto = productRequest.getProductImage();
//        way 1
//        for (ProductImageRequest pr: productPhoto) {
//            var productImage = ProductImage
//                            .builder()
//                            .photo(pr.getPhoto())
//                            .product(product)
//                            .build();
//            productImageRepository.save(productImage);
//        }

//       way 2
        List<ProductImage> productImage = productPhoto.stream().map(p -> ProductImage.builder()
                        .photo(p.getPhoto())
                        .product(product)
                        .build())
                .toList();
        productImageRepository.saveAll(productImage);

    }

    @Override
    public Object getAllProduct(Pageable pageable) {

        Page<Product> allProduct = productRepository.findByOrderByIdDesc(pageable);
        List<ProductResponse> productResponse = allProduct.stream()
                .map(product -> {
                    List<ProductImageResponse> productImages = product.getImages().stream()
                            .map(image -> ProductImageResponse
                                    .builder()
                                    .id(image.getId())
                                    .image_url(image.getPhoto())
                                    .build()
                            ).collect(Collectors.toList());

                    return ProductResponse.builder()
                            .productId(product.getId())
                            .productName(product.getProductName())
                            .salePrice(product.getSalePrice())
                            .availableUnit(product.getAvailableUnit())
                            .productImages(productImages)
                            .colorName(product.getColorId().getColorName())
                            .modelName(product.getModelId().getModelName())
                            .build();
                }).toList();


        // Test push notification
        notificationService.pushNotification(1L, "you got notification");

        return ProductMainResponse.builder()
                .products(productResponse)
                .page(allProduct)
                .build();
    }

    @Override
    public Product getProductById(Integer id) {
        return productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Product.class, "id", id.toString()));
    }

    @Override
    public Product getByModelIdAndColorId(Integer modelId, Integer colorId) {
        return productRepository.findByModelIdIdAndColorIdId(modelId, colorId).orElseThrow(() -> new EntityNotFoundException(Model.class, "id", modelId.toString(), "and Color with id", colorId.toString()));
    }

    @Override
    public void importProduct(ImportProductRequest importProductRequest) {

        //update available unit
        Product product = getProductById(importProductRequest.getProductId());
        int availableUnit = 0;
        if (product.getAvailableUnit() != null) {
            availableUnit = product.getAvailableUnit() + importProductRequest.getImportUnit();
            product.setAvailableUnit(availableUnit);
        } else {
            availableUnit = importProductRequest.getImportUnit();
            product.setAvailableUnit(availableUnit);
        }
        productRepository.save(product);

        //save product import history
        ProductImportHistory productImportHistory = ProductImportHistory.builder()
                .productId(product)
                .importUnit(importProductRequest.getImportUnit())
                .pricePerUnit(importProductRequest.getImportPrice())
                .dateImport(importProductRequest.getImportDate())
                .build();
        productImportRepository.save(productImportHistory);
    }

    @Override
    public Map<Integer, String> uploadProduct(MultipartFile file) {

        Map<Integer, String> map = new HashMap<>();

        try {
            Workbook workbook = new XSSFWorkbook(file.getInputStream());
            Sheet sheet = workbook.getSheet("product");
            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next();
            while (rowIterator.hasNext()) {
                int rowNumber = 0;
                try {
                    Row row = rowIterator.next();

                    int cellIndex = 0;

                    Cell cellNo = row.getCell(cellIndex++);
                    rowNumber = (int) cellNo.getNumericCellValue();

                    Cell cellModelId = row.getCell(cellIndex++);
                    Integer modelId = (int) cellModelId.getNumericCellValue();
                    System.err.println(modelId);

                    Cell cellColorId = row.getCell(cellIndex++);
                    Integer colorId = (int) cellColorId.getNumericCellValue();
                    System.err.println(colorId);

                    Cell cellImportPrice = row.getCell(cellIndex++);
                    BigDecimal importPrice = BigDecimal.ZERO;
                    // Check if the cell is not null and is of numeric type
                    if (cellImportPrice != null && cellImportPrice.getCellType() == CellType.NUMERIC) {
                        double numericValue = cellImportPrice.getNumericCellValue();
                        importPrice = BigDecimal.valueOf(numericValue);
                        System.err.println(importPrice);
                    } else {
                        System.err.println("error");
                    }

                    Cell cellImportUnit = row.getCell(cellIndex++);
                    Integer importUnit = (int) cellImportUnit.getNumericCellValue();
                    if (importUnit < 1) {
                        throw new BusinessException(StatusCode.IMPORT_UNIT_MUST_GREATER_THAN_ZERO);
                    }
                    System.err.println(importUnit);

                    Cell cellImportDate = row.getCell(cellIndex);
                    LocalDate importDate = cellImportDate.getLocalDateTimeCellValue().toLocalDate();
                    System.err.println(importDate);

                    Product product = getByModelIdAndColorId(modelId, colorId);

                    //save product
                    int availableUnit = 0;
                    if (product.getAvailableUnit() != null) {
                        availableUnit = product.getAvailableUnit() + importUnit;
                        product.setAvailableUnit(availableUnit);
                    } else {
                        availableUnit = importUnit;
                        product.setAvailableUnit(availableUnit);
                    }
                    productRepository.save(product);

                    //save product import history
                    ProductImportHistory productImportHistory = ProductImportHistory.builder()
                            .productId(product)
                            .importUnit(importUnit)
                            .pricePerUnit(importPrice)
                            .dateImport(importDate)
                            .build();
                    productImportRepository.save(productImportHistory);
                } catch (Exception e) {
                    map.put(rowNumber, e.getMessage());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return map;
    }

    @Override
    public void setSalePrice(Integer productId, PriceRequest price) throws Throwable {
        Product product = getProductById(productId);
        product.setSalePrice(price.getSalePrice());
        productRepository.save(product);
    }
}
