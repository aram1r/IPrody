package com.iprody.productservice.services;
import com.iprody.productservice.generated.api.ProductsApiDelegate;
import com.iprody.productservice.generated.model.ErrorResponse;
import com.iprody.productservice.generated.model.Product;
import com.iprody.productservice.model.ProductEntity;
import com.iprody.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
@Service
public class ProductsApiDelegateService implements ProductsApiDelegate {

    private ProductRepository productRepository;

    @Autowired
    public ProductsApiDelegateService (ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<Product> createProduct(Product productDto) {
        ProductEntity entity = new ProductEntity();
        entity.setName(productDto.getName());
        entity.setPrice(BigDecimal.valueOf(productDto.getPrice()));
        entity.setCurrency(productDto.getCurrency());
        entity.setDiscount(BigDecimal.valueOf(productDto.getDiscount()));
        entity.setActive(Boolean.TRUE.equals(productDto.getActive()));

        ProductEntity savedEntity = productRepository.save(entity);

        productDto.setId((int) savedEntity.getId());

        return ResponseEntity.status(201).body(productDto);
    }

    @Override
    public ResponseEntity<List<Product>> getAllProducts(Integer limit, Integer offset) {
        int pageNumber = (offset != null && limit != null && limit > 0) ? offset / limit : 0;
        int pageSize = (limit != null && limit > 0) ? limit : 25;

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);

        List<ProductEntity> entities =
                productRepository.findAll(pageRequest).getContent();

        List<Product> dtos = entities.stream()
                .map(this::mapToDto)
                .toList();

        return ResponseEntity.ok(dtos);
    }

    @Override
    public ResponseEntity<Product> getProductById(Integer id) {
        return productRepository.findById(Long.valueOf(id))
                .map(this::mapToDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Вспомогательный метод для маппинга Entity -> DTO
    private Product mapToDto(ProductEntity entity) {
        Product dto = new Product();
        dto.setId((int) entity.getId());
        dto.setName(entity.getName());
        dto.setPrice(entity.getPrice().floatValue());
        dto.setCurrency(entity.getCurrency());
        dto.setDiscount(entity.getDiscount() != null ? entity.getDiscount().floatValue() : 0.0f);
        dto.setActive(entity.isActive());
        return dto;
    }

    @Override
    public ResponseEntity<List<Product>> deactivateProduct(Integer id) {
        return productRepository.findById(Long.valueOf(id))
                .map(entity -> {
                    entity.setActive(false);
                    productRepository.save(entity);
                    Product dto = mapToDto(entity);
                    return ResponseEntity.ok(java.util.List.of(dto));
                })
                .orElseGet(() -> {
                    ErrorResponse error = new ErrorResponse();
                    error.setMessage("Product not found");
                    error.setDetails(java.util.List.of("Could not delete product. ID " + id + " does not exist."));
                    error.setResourceGuid(java.util.UUID.randomUUID().toString());

                    return new ResponseEntity(error, org.springframework.http.HttpStatus.NOT_FOUND);
                });
    }
}