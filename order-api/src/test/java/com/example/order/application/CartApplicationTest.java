package com.example.order.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.example.domain.domain.dto.AddProductCartForm;
import com.example.domain.domain.dto.AddProductForm;
import com.example.domain.domain.dto.AddProductItemForm;
import com.example.domain.domain.model.product.Product;
import com.example.domain.domain.redis.Cart;
import com.example.domain.domain.repository.ProductRepository;
import com.example.order.service.ProductService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CartApplicationTest {
    @Autowired
    private CartApplication cartApplication;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;

    @Test
    void ADD_TEST() {
        Long customerId = 100L;

        cartApplication.clearCart(customerId);

        Product p = add_product();
        Product result = productRepository.findWithProductItemsById(p.getId()).get();

        assertNotNull(result);

        assertEquals(result.getName(), ("나이키 에어포스"));
        assertEquals(result.getDescription(), ("신발"));
        assertEquals(result.getProductItems().size(), (3));
        assertEquals(result.getProductItems().get(0).getName(), "나이키 에어포스0");
        assertEquals(result.getProductItems().get(0).getPrice(), 10000);

        AddProductCartForm addProductCartForm = makeAddForm(result);

        Cart cart = cartApplication.addCart(customerId, addProductCartForm);

        assertEquals(cart.getMessages().size(),0);

        cart = cartApplication.getCart(customerId);
        assertEquals(cart.getMessages().size(),1);
    }

    AddProductCartForm makeAddForm(Product p) {
        AddProductCartForm.ProductItem productItem =
            AddProductCartForm.ProductItem.builder()
                .id(p.getProductItems().get(0).getId())
                .name(p.getProductItems().get(0).getName())
                .count(5)
                .price(2000)
                .build();
        return AddProductCartForm.builder()
            .Id(1L)
            .sellerId(p.getSellerId())
            .name(p.getName())
            .description(p.getDescription())
            .items(List.of(productItem))
            .build();
    }

    Product add_product() {
        Long sellerid = 1L;

        AddProductForm form = makeProductForm("나이키 에어포스", "신발", 3);

        return productService.addProduct(sellerid, form);
    }

    private static AddProductForm makeProductForm(String name, String description, int itemCount) {
        List<AddProductItemForm> itemForms = new ArrayList<>();
        for (int i = 0; i < itemCount; i++) {
            itemForms.add(makeProductItemForm(null, name + i));
        }
        return AddProductForm.builder()
            .name(name)
            .description(description)
            .addProductItemForms(itemForms)
            .build();
    }

    private static AddProductItemForm makeProductItemForm(Long productId, String name) {
        return AddProductItemForm.builder()
            .productId(productId)
            .name(name)
            .price(10000)
            .count(10)
            .build();
    }
}