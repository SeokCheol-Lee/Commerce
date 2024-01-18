package com.example.user.service.seller;

import com.example.domain.domain.model.seller.Seller;
import com.example.domain.domain.repository.SellerRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SellerService {

    private final SellerRepository sellerRepository;

    public Optional<Seller> findByIdAndEmail(Long id, String email){
        return sellerRepository.findByIdAndEmail(id,email);
    }
    public Optional<Seller> findValidSeller(String email, String password){
        return sellerRepository.findByEmailAndPassword(email, password);
    }
}
