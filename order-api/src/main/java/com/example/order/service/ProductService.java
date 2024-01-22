package com.example.order.service;

import com.example.domain.domain.dto.AddProductForm;
import com.example.domain.domain.dto.AddProductItemForm;
import com.example.domain.domain.model.product.Product;
import com.example.domain.domain.model.product.ProductItem;
import com.example.domain.domain.repository.ProductRepository;
import com.example.order.dto.UpdateProductForm;
import com.example.order.dto.UpdateProductItemForm;
import com.example.order.exception.CustomException;
import com.example.order.exception.ErrorCode;
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

    @Transactional
    public Product updateProduct(Long sellerId, UpdateProductForm form){
        Product product = productRepository.findBySellerIdAndId(sellerId, form.getId())
            .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_PRODUCT));
        product.setName(form.getName());
        product.setDescription(form.getDescription());

        for(UpdateProductItemForm itemForm : form.getItems()){
            ProductItem item = product.getProductItems().stream()
                .filter(pi->pi.getId().equals(itemForm.getId()))
                .findFirst().orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ITEM));
            item.setName(itemForm.getName());
            item.setPrice(itemForm.getPrice());
            item.setCount(itemForm.getCount());
        }
        return product;
    }
}
