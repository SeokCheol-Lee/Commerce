package com.example.order.service;

import com.example.domain.domain.model.product.Product;
import com.example.domain.domain.repository.ProductRepository;
import com.example.order.exception.CustomException;
import com.example.order.exception.ErrorCode;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductSearchService {

    private final ProductRepository productRepository;

    public Product getByProductId(Long productId){
        return productRepository.findWithProductItemsById(productId)
            .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_PRODUCT));
    }

    public List<Product> getListByProductIds(List<Long> productIds){
        return productRepository.findAllById(productIds);
    }

    public List<Product> searchByName(String name){
        return productRepository.searchByName(name);
    }
}
