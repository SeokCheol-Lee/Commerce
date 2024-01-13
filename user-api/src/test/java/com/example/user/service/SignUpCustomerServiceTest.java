package com.example.user.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import com.example.user.domain.SignUpForm;
import com.example.user.domain.model.Customer;
import com.example.user.domain.repository.CustomerRepository;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SignUpCustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;
    @InjectMocks
    private SignUpCustomerService signUpCustomerService;

    @Test
    void signUp() {
        //given
        SignUpForm signUpForm = SignUpForm.builder()
            .email("email")
            .name("name")
            .password("password")
            .birth(LocalDate.now())
            .phone("phone")
            .build();
        Customer customer = Customer.from(signUpForm);
        given(customerRepository.save(any()))
            .willReturn(customer);
        //when
        signUpCustomerService.signUp(signUpForm);
        //then
        assertEquals(signUpForm.getName(), customer.getName());
        assertEquals(signUpForm.getPassword(), customer.getPassword());
        assertEquals(signUpForm.getEmail(), customer.getEmail());
    }
}