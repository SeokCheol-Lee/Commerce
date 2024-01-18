package com.example.domain.domain.repository;

import com.example.domain.domain.model.seller.Seller;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, Long> {
    Optional<Seller> findByIdAndEmail(Long id, String email);
    Optional<Seller> findByEmailAndPassword(String email, String password);
    Optional<Seller> findByEmail(String email);
}
