package com.fastcampus.investment.api;

import com.fastcampus.investment.dto.APIDataResponse;
import com.fastcampus.investment.dto.InvestmentDto;
import com.fastcampus.investment.dto.ProductDto;
import com.fastcampus.investment.service.InvestmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class Apis {
    private final InvestmentService investmentService;

    @GetMapping("/product")
    public APIDataResponse<List<ProductDto>> getValidProducts() {
        return APIDataResponse.of(investmentService.getValidProducts());
    }

    @GetMapping("/investment")
    public APIDataResponse<List<InvestmentDto>> getInvestmentList(@RequestHeader("X-USER-ID") Long userId) {
        return APIDataResponse.of(investmentService.getInvestmentList(userId));
    }

    @PostMapping("/investment")
    public APIDataResponse<InvestmentDto> invest(
            @RequestHeader("X-USER-ID") Long userId,
            @RequestParam Long productId,
            @RequestParam Long investAmount
    ) {
        return APIDataResponse.of(investmentService.invest(userId, productId, investAmount));
    }

    @PutMapping("/investment/{productId}")
    public APIDataResponse<InvestmentDto> cancelInvestment(
            @RequestHeader("X-USER-ID") Long userId,
            @PathVariable Long productId
    ) {
        return APIDataResponse.of(investmentService.cancelInvestment(userId, productId));
    }
}
