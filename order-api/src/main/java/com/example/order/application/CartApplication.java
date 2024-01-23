package com.example.order.application;

import com.example.domain.domain.dto.AddProductCartForm;
import com.example.domain.domain.model.product.Product;
import com.example.domain.domain.model.product.ProductItem;
import com.example.domain.domain.redis.Cart;
import com.example.order.exception.CustomException;
import com.example.order.exception.ErrorCode;
import com.example.order.service.CartService;
import com.example.order.service.ProductSearchService;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartApplication {
    private final CartService cartService;
    private final ProductSearchService productSearchService;

    public Cart addCart(Long customerId, AddProductCartForm form){
        Product product = productSearchService.getByProductId(form.getId());
        if(product==null){
            throw new CustomException(ErrorCode.NOT_FOUND_PRODUCT);
        }

        Cart cart = cartService.getCart(customerId);
        if(cart != null && !addAble(cart,product,form)){
            throw new CustomException(ErrorCode.ITEM_COUNT_NOT_ENOUGH);
        }

        return cartService.addCart(customerId, form);
    }

    private boolean addAble(Cart cart, Product product,AddProductCartForm form){
        Cart.Product cartProduct = cart.getProducts().stream()
            .filter(p -> p.getId().equals(form.getId()))
            .findFirst().orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_PRODUCT));
        Map<Long,Integer> cartItemCount = cartProduct.getItems().stream()
            .collect(Collectors.toMap(Cart.ProductItem::getId,Cart.ProductItem::getCount));
        Map<Long,Integer> currentItemCountMap = product.getProductItems().stream()
            .collect(Collectors.toMap(ProductItem::getId,ProductItem::getCount));
        
        return form.getItems().stream().noneMatch(
            formItem -> {
                Integer cartCount = cartItemCount.get(formItem.getId());
                Integer currentCount = currentItemCountMap.get(formItem.getId());
                return formItem.getCount() + cartCount > currentCount;
            });
    }
}
