package com.example.user.controller;

import com.example.domain.domain.dto.SignUpForm;
import com.example.user.application.SignUpApplication;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signup")
@RequiredArgsConstructor
public class SignUpController {

    private final SignUpApplication signUpApplication;

    @PostMapping("/customer")
    public ResponseEntity<String> customerSignUp(@RequestBody SignUpForm form){
        return ResponseEntity.ok(signUpApplication.customerSignup(form));
    }

    @PostMapping("/seller")
    public ResponseEntity<String> sellerSignUp(@RequestBody SignUpForm form){
        return ResponseEntity.ok(signUpApplication.sellerSignUp(form));
    }
}
