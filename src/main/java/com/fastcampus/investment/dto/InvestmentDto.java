package com.fastcampus.investment.dto;

import com.fastcampus.investment.entity.Investment;
import com.fastcampus.investment.entity.Products;
import com.fastcampus.investment.type.InvestmentStatus;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class InvestmentDto {
    private Long id;
    private Long userId;
    private Products product;
    private Long investedAmount;
    private InvestmentStatus investmentStatus;
    private LocalDate investedAt;

    public static InvestmentDto from(Investment investment) {
        return InvestmentDto.builder()
                .id(investment.getId())
                .userId(investment.getUserId())
                .product(investment.getProduct())
                .investedAmount(investment.getInvestedAmount())
                .investmentStatus(investment.getInvestmentStatus())
                .investedAt(investment.getInvestedAt())
                .build();
    }
}
