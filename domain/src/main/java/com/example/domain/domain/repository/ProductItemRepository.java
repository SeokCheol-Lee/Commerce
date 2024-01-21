package com.example.domain.domain.repository;

import com.example.domain.domain.model.product.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductItemRepository extends JpaRepository<ProductItem, Long> {

}
