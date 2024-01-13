package com.example.user.application;

import com.example.user.domain.SignUpForm;
import com.example.user.domain.model.Customer;
import com.example.user.exception.CustomException;
import com.example.user.exception.ErrorCode;
import com.example.user.service.SignUpCustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpApplication {

    private final SignUpCustomerService signUpCustomerService;

    public String customerSignup(SignUpForm form){
        if(signUpCustomerService.isEmailExist(form.getEmail())){
            throw new CustomException(ErrorCode.ALREADY_REGISTER_USER);
        }else{
            Customer c = signUpCustomerService.signUp(form);

            return "회원가입 성공";
        }
    }
}
