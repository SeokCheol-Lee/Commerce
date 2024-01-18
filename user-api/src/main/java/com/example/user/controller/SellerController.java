package com.example.user.controller;

import com.example.domain.config.JwtAuthenticationiProvider;
import com.example.domain.domain.common.UserVo;
import com.example.user.dto.ChangeBalanceForm;
import com.example.user.dto.SellerDto;
import com.example.domain.domain.model.seller.Seller;
import com.example.user.exception.CustomException;
import com.example.user.exception.ErrorCode;
import com.example.user.service.seller.SellerBalanceService;
import com.example.user.service.seller.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seller")
@RequiredArgsConstructor
public class SellerController {

    private final JwtAuthenticationiProvider provider;
    private final SellerService sellerService;
    private final SellerBalanceService sellerBalanceService;

    @GetMapping("/getInfo")
    public ResponseEntity<SellerDto> getInfo(@RequestHeader(name = "X-AUTH-TOKEN") String token){
        UserVo vo = provider.getUserVo(token);
        Seller s = sellerService.findByIdAndEmail(vo.getId(), vo.getEmail()).orElseThrow(
            () -> new CustomException(ErrorCode.NOT_FOUND_USER));
        return ResponseEntity.ok(SellerDto.from(s));
    }

    @PostMapping("/balance")
    public ResponseEntity<Integer> changeBalance(@RequestHeader(name = "X-AUTH-TOKEN") String token,
        @RequestBody ChangeBalanceForm form){
        UserVo vo = provider.getUserVo(token);
        return ResponseEntity.ok(sellerBalanceService.changeBalance(vo.getId(),form).getCurrentMoney());
    }
}
