package com.example.domain.domain.repository;

import com.example.domain.domain.model.seller.SellerBalanceHistory;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestParam;

public interface SellerBalanceHistoryRepository extends JpaRepository<SellerBalanceHistory, Long> {
    Optional<SellerBalanceHistory> findFirstBySeller_IdOrderByIdDesc(
        @RequestParam("seller_id") Long sellerId);
}
