package com.fastcampus.investment.exception;

import lombok.Getter;

@Getter
public class InvestmentException extends RuntimeException {
    private InvestmentErrorCode errorCode;
    private String message;

    public InvestmentException(InvestmentErrorCode errorCode) {
        this.errorCode = errorCode;
        this.message = errorCode.getMessage();
    }

}
