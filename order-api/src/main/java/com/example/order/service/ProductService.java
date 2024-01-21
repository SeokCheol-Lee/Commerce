package com.example.order.service;

import com.example.domain.domain.model.product.Product;
import com.example.domain.domain.repository.ProductRepository;
import com.example.domain.domain.dto.AddProductForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public Product addProduct(Long sellerId, AddProductForm form){
        return productRepository.save(Product.of(sellerId,form));
    }
}
