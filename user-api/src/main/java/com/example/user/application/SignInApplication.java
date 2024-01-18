package com.example.user.application;

import com.example.domain.config.JwtAuthenticationiProvider;
import com.example.domain.domain.common.UserType;
import com.example.user.dto.SignInForm;
import com.example.domain.domain.model.customer.Customer;
import com.example.domain.domain.model.seller.Seller;
import com.example.user.exception.CustomException;
import com.example.user.exception.ErrorCode;
import com.example.user.service.customer.CustomerService;
import com.example.user.service.seller.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignInApplication {

    private final CustomerService customerService;
    private final SellerService sellerService;
    private final JwtAuthenticationiProvider provider;

    public String customerLoginToken(SignInForm form){
        Customer c = customerService.findValidCustomer(form.getEmail(), form.getPassword())
            .orElseThrow(() -> new CustomException(ErrorCode.LOGIN_CHECK_FAIL));

        return provider.createToken(c.getEmail(),c.getId(), UserType.CUSTOMER);
    }

    public String sellerLoginToken(SignInForm form){
        Seller s = sellerService.findValidSeller(form.getEmail(), form.getPassword())
            .orElseThrow(() -> new CustomException(ErrorCode.LOGIN_CHECK_FAIL));
        return provider.createToken(s.getEmail(),s.getId(), UserType.SELLER);
    }
}
