package com.example.order.controller;

import com.example.domain.config.JwtAuthenticationiProvider;
import com.example.domain.domain.dto.AddProductCartForm;
import com.example.domain.domain.redis.Cart;
import com.example.order.application.CartApplication;
import com.example.order.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer/cart")
@RequiredArgsConstructor
public class CustomerCartController {

    private final CartApplication cartApplication;
    private final JwtAuthenticationiProvider provider;

    @PostMapping
    public ResponseEntity<Cart> addCart(
        @RequestHeader(name = "X-AUTH-TOKEN") String token,
        @RequestBody AddProductCartForm form){
        return ResponseEntity.ok(cartApplication.addCart(provider.getUserVo(token).getId(),form));
    }
}
