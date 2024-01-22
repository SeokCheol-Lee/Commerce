package com.example.domain.domain.repository;

import com.example.domain.domain.model.product.Product;
import java.util.List;

public interface ProductRepositoryCustom {
    List<Product> searchByName(String name);
}
