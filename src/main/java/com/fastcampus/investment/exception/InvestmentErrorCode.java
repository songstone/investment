package com.fastcampus.investment.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum InvestmentErrorCode {
    INVESTMENT_AMOUNT_NOT_MATCHED("투자 가능한 금액을 초과했습니다."),
    NO_PRODUCT("해당 상품이 없습니다."),
    NO_INVESTMENT("투자 내역이 없습니다."),
    INTERNAL_SERVER_ERROR("서버 내부 오류");

    private final String message;
}
