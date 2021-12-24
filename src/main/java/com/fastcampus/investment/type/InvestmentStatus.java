package com.fastcampus.investment.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum InvestmentStatus {

    INVESTING("투자중"),
    CANCELED("취소"),
    FAIL("투자실패");
    private final String description;
}
