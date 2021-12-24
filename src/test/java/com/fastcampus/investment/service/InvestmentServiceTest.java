package com.fastcampus.investment.service;

import com.fastcampus.investment.entity.Investment;
import com.fastcampus.investment.entity.Products;
import com.fastcampus.investment.exception.InvestmentErrorCode;
import com.fastcampus.investment.exception.InvestmentException;
import com.fastcampus.investment.repository.InvestmentRepository;
import com.fastcampus.investment.repository.ProductRepository;
import com.fastcampus.investment.type.InvestmentStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class InvestmentServiceTest {

    @Mock
    private InvestmentRepository investmentRepository;
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private InvestmentService investmentService;

    Products testProduct = Products.builder()
            .title("테스트")
            .totalInvestingAmount(10000L)
            .investedAmount(0L)
            .investedCount(0)
            .startedAt(LocalDate.now().minusDays(1L))
            .finishedAt(LocalDate.now().plusDays(1L))
            .build();

    Investment testInvestment = Investment.builder()
            .userId(1L)
            .product(testProduct)
            .investedAmount(2000L)
            .investmentStatus(InvestmentStatus.INVESTING)
            .investedAt(LocalDate.now())
            .build();

    @DisplayName("투자중 해당 상품이 없을 때 예외 발생")
    @Test
    void investNoProductTest() {
        //given
        given(productRepository.findById(anyLong()))
                .willReturn(Optional.empty());
        //when
        //then
        InvestmentException investmentException = assertThrows(InvestmentException.class,
                () -> investmentService.invest(1L, 1L, 10000L)
        );
        assertEquals(InvestmentErrorCode.NO_PRODUCT, investmentException.getErrorCode());
    }

    @DisplayName("상품의 투자 가능 금액 초과시 FAIL 반환")
    @Test
    void validateInvestAmountTest() {
        assertEquals(InvestmentStatus.FAIL, investmentService.validateInvestAmount(testProduct, 20000L));
    }


    @DisplayName("투자 취소중 투자내역이 없을 때 예외 발생")
    @Test
    void cancelInvestmentNoInvestmentTest() {
        //given
        given(productRepository.findById(anyLong()))
                .willReturn(Optional.of(testProduct));
        //when
        //then
        InvestmentException investmentException = assertThrows(InvestmentException.class,
                () -> investmentService.cancelInvestment(1L, 1L)
        );
        assertEquals(InvestmentErrorCode.NO_INVESTMENT, investmentException.getErrorCode());
    }


}