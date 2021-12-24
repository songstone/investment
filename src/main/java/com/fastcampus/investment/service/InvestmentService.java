package com.fastcampus.investment.service;


import com.fastcampus.investment.dto.InvestmentDto;
import com.fastcampus.investment.dto.ProductDto;
import com.fastcampus.investment.entity.Investment;
import com.fastcampus.investment.entity.Products;
import com.fastcampus.investment.exception.InvestmentException;
import com.fastcampus.investment.repository.InvestmentRepository;
import com.fastcampus.investment.repository.ProductRepository;
import com.fastcampus.investment.type.InvestmentStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static com.fastcampus.investment.exception.InvestmentErrorCode.NO_INVESTMENT;
import static com.fastcampus.investment.exception.InvestmentErrorCode.NO_PRODUCT;
import static com.fastcampus.investment.type.InvestmentStatus.*;

@Service
@RequiredArgsConstructor
public class InvestmentService {
    private final ProductRepository productRepository;
    private final InvestmentRepository investmentRepository;

    public List<ProductDto> getValidProducts() {
        LocalDate curDate = LocalDate.now();
        return productRepository.findByStartedAtLessThanAndFinishedAtGreaterThan(curDate, curDate)
                .stream().map(ProductDto::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public InvestmentDto invest(Long userId, Long productId, Long investAmount) {
        Products product = productRepository.findById(productId).orElseThrow(
                () -> new InvestmentException(NO_PRODUCT)
        );
        Investment investment = Investment.builder()
                .userId(userId)
                .product(product)
                .investedAmount(investAmount)
                .investmentStatus(validateInvestAmount(product, investAmount))
                .investedAt(LocalDate.now())
                .build();
        investmentRepository.save(investment);

        return InvestmentDto.from(investment);
    }

    public InvestmentStatus validateInvestAmount(Products product, Long investAmount) {
        if (product.getTotalInvestingAmount() - product.getInvestedAmount() < investAmount) {
            return FAIL;
        }
        return INVESTING;
    }

    public List<InvestmentDto> getInvestmentList(Long userId) {
        return investmentRepository.findByUserIdEqualsOrderByIdAsc(userId)
                .stream().map(InvestmentDto::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public InvestmentDto cancelInvestment(Long userId, Long productId) {
        Products product = productRepository.findById(productId)
                .orElseThrow(
                        () -> new InvestmentException(NO_PRODUCT)
                );
        Investment investment = investmentRepository.findByUserIdEqualsAndProductEquals(userId, product)
                .orElseThrow(
                        () -> new InvestmentException(NO_INVESTMENT)
                );
        investment.setInvestmentStatus(CANCELED);
        investmentRepository.save(investment);

        return InvestmentDto.from(investment);
    }


}
