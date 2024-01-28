package com.example.order.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {
    NOT_FOUND_PRODUCT(HttpStatus.BAD_REQUEST,"상품을 찾을 수 없습니다."),
    NOT_FOUND_ITEM(HttpStatus.BAD_REQUEST,"아이템을 찾을 수 없습니다."),
    ITEM_COUNT_NOT_ENOUGH(HttpStatus.BAD_REQUEST,"상품의 수량이 부족합니다."),
    ORDER_FAIL_CHECK_CART(HttpStatus.BAD_REQUEST,"주문불가, 장바구니 확인이 필요합니다."),
    ORDER_FAIL_NOT_ENOUGH_MONEY(HttpStatus.BAD_REQUEST,"주문불가, 잔액이 부족합니다."),
    SAME_ITEM_NAME(HttpStatus.BAD_REQUEST,"중복된 아이템 명이 존재합니다.");

    private final HttpStatus httpStatus;
    private final String detail;
}
