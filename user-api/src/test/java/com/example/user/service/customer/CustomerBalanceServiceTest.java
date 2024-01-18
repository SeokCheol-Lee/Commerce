package com.example.user.service.customer;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.example.user.dto.ChangeBalanceForm;
import com.example.domain.domain.model.customer.Customer;
import com.example.domain.domain.model.customer.CustomerBalanceHistory;
import com.example.domain.domain.repository.CustomerBalanceHistoryRepository;
import com.example.domain.domain.repository.CustomerRepository;
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
class CustomerBalanceServiceTest {

    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private CustomerBalanceHistoryRepository customerBalanceHistoryRepository;
    @InjectMocks
    private CustomerBalanceService customerBalanceService;

    @Test
    void changeBalance() {
        //given
        ArgumentCaptor<CustomerBalanceHistory> captor =
            ArgumentCaptor.forClass(CustomerBalanceHistory.class);
        ChangeBalanceForm form = ChangeBalanceForm.builder()
            .from("form")
            .message("message")
            .money(5000).build();
        Customer customer = Customer.builder()
            .id(1L).build();
        CustomerBalanceHistory balanceHistory = CustomerBalanceHistory.builder()
            .customer(customer)
            .changeMoney(0)
            .currentMoney(0).build();
        given(customerRepository.findById(any()))
            .willReturn(Optional.ofNullable(customer));
        given(customerBalanceHistoryRepository.findFirstByCustomer_IdOrderByIdDesc(any()))
            .willReturn(Optional.ofNullable(balanceHistory));
        //when
        customerBalanceService.changeBalance(1L, form);
        verify(customerBalanceHistoryRepository,times(1)).save(captor.capture());
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
        Customer customer = Customer.builder()
            .id(1L).build();
        given(customerRepository.findById(any()))
            .willReturn(Optional.empty());
        //when
        CustomException customException = assertThrows(CustomException.class,
            () -> customerBalanceService.changeBalance(1L,form));
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
        Customer customer = Customer.builder()
            .id(1L).build();
        CustomerBalanceHistory balanceHistory = CustomerBalanceHistory.builder()
            .customer(customer)
            .changeMoney(0)
            .currentMoney(0).build();
        given(customerRepository.findById(any()))
            .willReturn(Optional.ofNullable(customer));
        given(customerBalanceHistoryRepository.findFirstByCustomer_IdOrderByIdDesc(any()))
            .willReturn(Optional.ofNullable(balanceHistory));
        //when
        CustomException customException = assertThrows(CustomException.class,
            () -> customerBalanceService.changeBalance(1L,form));
        //then
        assertEquals(ErrorCode.NOT_ENOUGH_BALANCE,customException.getErrorCode());
    }
}