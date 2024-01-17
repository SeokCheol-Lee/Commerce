package com.example.user.service.seller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import com.example.user.domain.SignUpForm;
import com.example.user.domain.model.seller.Seller;
import com.example.user.domain.repository.SellerRepository;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SignUpSellerServiceTest {

    @Mock
    private SellerRepository sellerRepository;
    @InjectMocks
    private SignUpSellerService signUpSellerService;

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
        Seller seller = Seller.from(signUpForm);
        given(sellerRepository.save(any()))
            .willReturn(seller);
        //when
        Seller s = signUpSellerService.signUp(signUpForm);
        //then
        assertEquals(signUpForm.getName(), s.getName());
        assertEquals(signUpForm.getPassword(), s.getPassword());
        assertEquals(signUpForm.getEmail(), s.getEmail());
    }
}