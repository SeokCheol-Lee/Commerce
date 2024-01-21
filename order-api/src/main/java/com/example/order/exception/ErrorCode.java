package com.example.order.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {
    NOT_FOUNT_PRODUCT(HttpStatus.BAD_REQUEST,"상품을 찾을 수 없습니다."),
    SAME_ITEM_NAME(HttpStatus.BAD_REQUEST,"중복된 아이템 명이 존재합니다.");

    private final HttpStatus httpStatus;
    private final String detail;
}