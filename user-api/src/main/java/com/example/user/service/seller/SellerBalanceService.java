package com.example.user.service.seller;

import com.example.user.dto.ChangeBalanceForm;
import com.example.domain.domain.model.seller.SellerBalanceHistory;
import com.example.domain.domain.repository.SellerBalanceHistoryRepository;
import com.example.domain.domain.repository.SellerRepository;
import com.example.user.exception.CustomException;
import com.example.user.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SellerBalanceService {

    private final SellerBalanceHistoryRepository sellerBalanceHistoryRepository;
    private final SellerRepository sellerRepository;

    @Transactional(noRollbackFor = {CustomException.class})
    public SellerBalanceHistory changeBalance(Long sellerId, ChangeBalanceForm form) throws CustomException{
        SellerBalanceHistory sellerBalanceHistory =
            sellerBalanceHistoryRepository.findFirstBySeller_IdOrderByIdDesc(sellerId)
                .orElse(SellerBalanceHistory.builder()
                    .changeMoney(0)
                    .currentMoney(0)
                    .seller(sellerRepository.findById(sellerId)
                        .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER)))
                    .build());
        if(sellerBalanceHistory.getCurrentMoney() + form.getMoney() < 0){
            throw new CustomException(ErrorCode.NOT_ENOUGH_BALANCE);
        }

        sellerBalanceHistory = SellerBalanceHistory.builder()
            .changeMoney(form.getMoney())
            .currentMoney(sellerBalanceHistory.getCurrentMoney() + form.getMoney())
            .description(form.getMessage())
            .fromMessage(form.getFrom())
            .seller(sellerBalanceHistory.getSeller())
            .build();

        sellerBalanceHistory.getSeller().setBalance(sellerBalanceHistory.getCurrentMoney());
        return sellerBalanceHistoryRepository.save(sellerBalanceHistory);
    }
}
