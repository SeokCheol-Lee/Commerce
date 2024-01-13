package com.example.user.application;

import com.example.domain.config.JwtAuthenticationiProvider;
import com.example.domain.domain.common.UserType;
import com.example.user.domain.SignInForm;
import com.example.user.domain.model.Customer;
import com.example.user.exception.CustomException;
import com.example.user.exception.ErrorCode;
import com.example.user.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignInApplication {

    private final CustomerService customerService;
    private final JwtAuthenticationiProvider provider;

    public String customerLoginToken(SignInForm form){
        Customer c = customerService.findValidCustomer(form.getEmail(), form.getPassword())
            .orElseThrow(() -> new CustomException(ErrorCode.LOGIN_CHECK_FAIL));
        

        return provider.createToken(c.getEmail(),c.getId(), UserType.CUSTOMER);
    }
}
