package com.iprody.productservice.repository;
import com.iprody.productservice.generated.model.Product;
import com.iprody.productservice.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}