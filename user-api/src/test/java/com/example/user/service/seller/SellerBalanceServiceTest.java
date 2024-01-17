package com.example.user.service.seller;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.example.user.domain.ChangeBalanceForm;
import com.example.user.domain.model.customer.Customer;
import com.example.user.domain.model.customer.CustomerBalanceHistory;
import com.example.user.domain.model.seller.Seller;
import com.example.user.domain.model.seller.SellerBalanceHistory;
import com.example.user.domain.repository.SellerBalanceHistoryRepository;
import com.example.user.domain.repository.SellerRepository;
import com.example.user.exception.CustomException;
import com.example.user.exception.ErrorCode;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SellerBalanceServiceTest {

    @Mock
    private SellerRepository sellerRepository;
    @Mock
    private SellerBalanceHistoryRepository sellerBalanceHistoryRepository;
    @InjectMocks
    private SellerBalanceService sellerBalanceService;

    @Test
    void changeBalance() {
        //given
        ArgumentCaptor<SellerBalanceHistory> captor =
            ArgumentCaptor.forClass(SellerBalanceHistory.class);
        ChangeBalanceForm form = ChangeBalanceForm.builder()
            .from("form")
            .message("message")
            .money(5000).build();
        Seller seller = Seller.builder()
            .id(1L).build();
        SellerBalanceHistory balanceHistory = SellerBalanceHistory.builder()
            .seller(seller)
            .changeMoney(0)
            .currentMoney(0).build();
        given(sellerRepository.findById(any()))
            .willReturn(Optional.ofNullable(seller));
        given(sellerBalanceHistoryRepository.findFirstBySeller_IdOrderByIdDesc(any()))
            .willReturn(Optional.ofNullable(balanceHistory));
        //when
        sellerBalanceService.changeBalance(1L,form);
        verify(sellerBalanceHistoryRepository,times(1)).save(captor.capture());
        //then
        assertEquals(form.getMoney(), captor.getValue().getCurrentMoney());
    }

    @Test
    @DisplayName("예치금 정리 실패 - 해당되는 유저가 없음")
    void changeBalance_UserNotFound(){
        //given
        ChangeBalanceForm form = ChangeBalanceForm.builder()
            .from("form")
            .message("message")
            .money(5000).build();
        Seller seller = Seller.builder()
            .id(1L).build();
        given(sellerRepository.findById(any()))
            .willReturn(Optional.empty());
        //when
        CustomException customException = assertThrows(CustomException.class,
            () -> sellerBalanceService.changeBalance(1L,form));
        //then
        assertEquals(ErrorCode.NOT_FOUND_USER, customException.getErrorCode());
    }
    @Test
    @DisplayName("예치금 정리 실패 - 잔액이 부족함")
    void changeBalance_OutOfBalance() {
        //given
        ChangeBalanceForm form = ChangeBalanceForm.builder()
            .from("form")
            .message("message")
            .money(-5000).build();
        Seller seller = Seller.builder()
            .id(1L).build();
        SellerBalanceHistory balanceHistory = SellerBalanceHistory.builder()
            .seller(seller)
            .changeMoney(0)
            .currentMoney(0).build();
        given(sellerRepository.findById(any()))
            .willReturn(Optional.ofNullable(seller));
        given(sellerBalanceHistoryRepository.findFirstBySeller_IdOrderByIdDesc(any()))
            .willReturn(Optional.ofNullable(balanceHistory));
        //when
        CustomException customException = assertThrows(CustomException.class,
            () -> sellerBalanceService.changeBalance(1L,form));
        //then
        assertEquals(ErrorCode.NOT_ENOUGH_BALANCE,customException.getErrorCode());
    }
}