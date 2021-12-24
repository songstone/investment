package com.fastcampus.investment.dto;

import com.fastcampus.investment.entity.Products;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ProductDto {
    private Long id;
    private String title;
    private Long totalInvestingAmount;
    private Long investedAmount;
    private Integer investedCount;
    private LocalDate startedAt;
    private LocalDate finishedAt;

    public static ProductDto from(Products product) {
        return ProductDto.builder()
                .id(product.getId())
                .title(product.getTitle())
                .totalInvestingAmount(product.getTotalInvestingAmount())
                .investedAmount(product.getInvestedAmount())
                .investedCount(product.getInvestedCount())
                .startedAt(product.getStartedAt())
                .finishedAt(product.getFinishedAt())
                .build();
    }
}
