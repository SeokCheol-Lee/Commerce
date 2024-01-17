package com.example.user.service.customer;

import com.example.user.domain.model.customer.Customer;
import com.example.user.domain.repository.CustomerRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Optional<Customer> findByIdAndEmail(Long id,String email){
        return customerRepository.findById(id)
            .stream().filter(customer -> customer.getEmail().equals(email)).findFirst();
    }

    public Optional<Customer> findValidCustomer(String email, String password){
        return customerRepository.findByEmail(email)
            .filter(customer -> customer.getPassword().equals(password))
            .stream().findFirst();
    }
}
