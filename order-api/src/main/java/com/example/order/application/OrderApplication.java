package com.example.order.application;

import com.example.domain.domain.model.product.ProductItem;
import com.example.domain.domain.redis.Cart;
import com.example.domain.domain.repository.ProductItemRepository;
import com.example.order.client.UserClient;
import com.example.order.client.user.ChangeBalanceForm;
import com.example.order.client.user.CustomerDto;
import com.example.order.exception.CustomException;
import com.example.order.exception.ErrorCode;
import com.example.order.service.ProductItemService;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderApplication {

    private final CartApplication cartApplication;
    private final UserClient userClient;
    private final ProductItemService productItemService;

    @Transactional
    public void order(String token, Cart cart){
        Cart orderCart = cartApplication.refreshCart(cart);
        if(orderCart.getMessages().size() > 0){
            throw new CustomException(ErrorCode.ORDER_FAIL_CHECK_CART);
        }
        CustomerDto customerDto = userClient.getCustomerInfo(token).getBody();

        int totalPrice = getTotalPrice(cart);
        if(customerDto.getBalance() < totalPrice){
            throw new CustomException(ErrorCode.ORDER_FAIL_NOT_ENOUGH_MONEY);
        }
        userClient.changeBalance(token,
            ChangeBalanceForm.builder()
            .from("USER")
            .message("Order")
            .money(-totalPrice)
            .build());
        for(Cart.Product product : orderCart.getProducts()){
            for(Cart.ProductItem cartItem : product.getItems()){
                ProductItem productItem = productItemService.getProductItem(cartItem.getId());
                productItem.setCount(productItem.getCount() - cartItem.getCount());
            }
        }
    }

    private Integer getTotalPrice(Cart cart){
        return cart.getProducts().stream().flatMapToInt(
            product -> product.getItems().stream().flatMapToInt(
                productItem -> IntStream.of(productItem.getPrice() * productItem.getCount())))
            .sum();
    }
}
