package com.example.user.dto;

import com.example.domain.domain.model.customer.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CustomerDto {

    private Long id;
    private String email;
    private Integer balance;

    public static CustomerDto from(Customer customer){
       return new CustomerDto(
           customer.getId(),customer.getEmail(),customer.getBalance());
    }
}
