package com.example.order.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import com.example.domain.domain.dto.AddProductForm;
import com.example.domain.domain.dto.AddProductItemForm;
import com.example.domain.domain.model.product.Product;
import com.example.domain.domain.model.product.ProductItem;
import com.example.domain.domain.repository.ProductItemRepository;
import com.example.domain.domain.repository.ProductRepository;
import com.example.order.dto.UpdateProductItemForm;
import com.example.order.dto.UpdateProductItemForm.UpdateProductItemFormBuilder;
import com.example.order.exception.CustomException;
import com.example.order.exception.ErrorCode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProductItemServiceTest {

    @Mock
    private ProductRepository productRepository;
    @Mock
    private ProductItemRepository productItemRepository;
    @InjectMocks
    private ProductItemService productItemService;

    @Test
    void addProductItem() {
        //given
        AddProductItemForm addProductItemForm = AddProductItemForm.builder()
            .productId(1L)
            .name("test")
            .price(1000)
            .count(100).build();
        Product product = Product.builder()
            .id(1L)
            .sellerId(1L)
            .name("test")
            .description("string")
            .productItems(new ArrayList<>()).build();
        ProductItem productItem = ProductItem.of(1L,addProductItemForm);
        given(productRepository.findBySellerIdAndId(any(),any()))
            .willReturn(Optional.ofNullable(product));
        //when
        Product testProduct = productItemService.addProductItem(1L, addProductItemForm);
        //then
        assertEquals(testProduct.getProductItems().get(0).getProduct(),productItem.getProduct());
    }
    @Test
    @DisplayName("상품아이템 추가 실패 - 동일한 아이템 존재")
    void addProductItem_SameItem() {
        //given
        AddProductItemForm addProductItemForm = AddProductItemForm.builder()
            .productId(1L)
            .name("test")
            .price(1000)
            .count(100).build();
        ProductItem productItem = ProductItem.of(1L,addProductItemForm);
        List<ProductItem> productItems = new ArrayList<>();
        productItems.add(productItem);
        Product product = Product.builder()
            .id(1L)
            .sellerId(1L)
            .name("test")
            .description("string")
            .productItems(productItems).build();

        given(productRepository.findBySellerIdAndId(any(),any()))
            .willReturn(Optional.ofNullable(product));
        //when
        CustomException customException = assertThrows(CustomException.class,
            () -> productItemService.addProductItem(1L, addProductItemForm));
        //then
        assertEquals(ErrorCode.SAME_ITEM_NAME,customException.getErrorCode());
    }

    @Test
    void updateProductItem() {
        //given
        AddProductItemForm addProductItemForm = AddProductItemForm.builder()
            .productId(1L)
            .name("test")
            .price(1000)
            .count(100).build();
        UpdateProductItemForm updateProductItemForm= UpdateProductItemForm.builder()
            .name("aaa")
            .price(9000)
            .count(900).build();
        ProductItem productItem = ProductItem.of(1L,addProductItemForm);
        given(productItemRepository.findById(any()))
            .willReturn(Optional.ofNullable(productItem));
        //when
        ProductItem updated = productItemService.updateProductItem(1L, updateProductItemForm);
        //then
        assertEquals(updated.getPrice(),9000);
        assertEquals(updated.getName(),"aaa");
        assertEquals(updated.getCount(),900);
    }
    @Test
    @DisplayName("상품아이템 수정 실패 - 해당하는 아이템이 존재하지 않음")
    void updateProductItem_NotFoundItem() {
        //given
        AddProductItemForm addProductItemForm = AddProductItemForm.builder()
            .productId(1L)
            .name("test")
            .price(1000)
            .count(100).build();
        UpdateProductItemForm updateProductItemForm= UpdateProductItemForm.builder()
            .name("aaa")
            .price(9000)
            .count(900).build();
        ProductItem productItem = ProductItem.of(1L,addProductItemForm);
        given(productItemRepository.findById(any()))
            .willReturn(Optional.ofNullable(productItem));
        //when
        CustomException customException = assertThrows(CustomException.class,
            () -> productItemService.updateProductItem(2L, updateProductItemForm));
        //then
        assertEquals(ErrorCode.NOT_FOUND_ITEM, customException.getErrorCode());
    }
}