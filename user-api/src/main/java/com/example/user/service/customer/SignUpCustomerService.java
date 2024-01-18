package com.example.user.service.customer;

import com.example.domain.domain.dto.SignUpForm;
import com.example.domain.domain.model.customer.Customer;
import com.example.domain.domain.repository.CustomerRepository;
import java.util.Locale;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpCustomerService {

    private final CustomerRepository customerRepository;
    public Customer signUp(SignUpForm form){
        return customerRepository.save(Customer.from(form));
    }

    public boolean isEmailExist(String email){
        return customerRepository.findByEmail(email.toLowerCase(Locale.ROOT)).isPresent();
    }
}
