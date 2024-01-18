package com.example.user.application;

import com.example.domain.domain.dto.SignUpForm;
import com.example.domain.domain.model.customer.Customer;
import com.example.domain.domain.model.seller.Seller;
import com.example.user.exception.CustomException;
import com.example.user.exception.ErrorCode;
import com.example.user.service.customer.SignUpCustomerService;
import com.example.user.service.seller.SignUpSellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpApplication {

    private final SignUpCustomerService signUpCustomerService;
    private final SignUpSellerService signUpSellerService;

    public String customerSignup(SignUpForm form){
        if(signUpCustomerService.isEmailExist(form.getEmail())){
            throw new CustomException(ErrorCode.ALREADY_REGISTER_USER);
        }else{
            Customer c = signUpCustomerService.signUp(form);

            return "회원가입 성공";
        }
    }

    public String sellerSignUp(SignUpForm form){
        if(signUpSellerService.isEmailExist(form.getEmail())){
            throw new CustomException(ErrorCode.ALREADY_REGISTER_USER);
        }else{
            Seller s = signUpSellerService.signUp(form);
            return "회워가입 성공";
        }
    }
}
