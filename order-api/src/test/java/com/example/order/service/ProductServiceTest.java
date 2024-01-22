package com.example.order.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.example.domain.domain.dto.AddProductForm;
import com.example.domain.domain.dto.AddProductItemForm;
import com.example.domain.domain.model.product.Product;
import com.example.domain.domain.model.product.ProductItem;
import com.example.domain.domain.repository.ProductRepository;
import com.example.order.dto.UpdateProductForm;
import com.example.order.dto.UpdateProductForm.UpdateProductFormBuilder;
import com.example.order.dto.UpdateProductItemForm;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private ProductService productService;

    @Test
    void addProduct() {
        //given
        ArgumentCaptor<Product> captor =
            ArgumentCaptor.forClass(Product.class);
        AddProductForm form = AddProductForm.builder()
            .name("test")
            .description("test")
            .addProductItemForms(new ArrayList<>()).build();
        Product product = Product.of(1L, form);
        //when
        productService.addProduct(1L, form);
        verify(productRepository,times(1)).save(captor.capture());
        //then
        assertEquals(captor.getValue().getName(),product.getName());
    }

    @Test
    void updateProduct() {
        //given
        AddProductForm form = AddProductForm.builder()
            .name("test")
            .description("test")
            .addProductItemForms(new ArrayList<>()).build();
        Product product = Product.of(1L, form);
        UpdateProductForm items = UpdateProductForm.builder()
            .name("test2")
            .description("test2")
            .items(new ArrayList<>()).build();
        given(productRepository.findBySellerIdAndId(any(),any()))
            .willReturn(Optional.ofNullable(product));
        //when
        Product updated = productService.updateProduct(1L, items);
        //then
        assertEquals(updated.getName(),"test2");
    }
}