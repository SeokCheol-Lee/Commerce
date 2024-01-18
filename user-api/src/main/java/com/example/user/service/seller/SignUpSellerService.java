package com.example.user.service.seller;

import com.example.domain.domain.dto.SignUpForm;
import com.example.domain.domain.model.seller.Seller;
import com.example.domain.domain.repository.SellerRepository;
import java.util.Locale;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpSellerService {

    private final SellerRepository sellerRepository;

    public Seller signUp(SignUpForm form){
        return sellerRepository.save(Seller.from(form));
    }

    public boolean isEmailExist(String email){
        return sellerRepository.findByEmail(email.toLowerCase(Locale.ROOT)).isPresent();
    }
}
