package com.example.user.dto;

import com.example.domain.domain.model.seller.Seller;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SellerDto {

    private Long id;
    private String email;

    public static SellerDto from(Seller seller){
        return new SellerDto(seller.getId(),seller.getEmail());
    }
}
