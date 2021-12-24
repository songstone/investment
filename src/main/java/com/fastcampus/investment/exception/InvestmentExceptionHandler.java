package com.fastcampus.investment.exception;

import com.fastcampus.investment.dto.InvestmentErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

import static com.fastcampus.investment.exception.InvestmentErrorCode.INTERNAL_SERVER_ERROR;

@Slf4j
@RestControllerAdvice
public class InvestmentExceptionHandler {

    @ExceptionHandler(InvestmentException.class)
    public InvestmentErrorResponse handleInvestmentException(InvestmentException e, HttpServletRequest request) {
        log.error("errorCode : {}, uri : {} , message{}", e.getErrorCode(), request.getRequestURI(), e.getMessage());
        return InvestmentErrorResponse.builder()
                .errorCode(e.getErrorCode())
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler(Exception.class)
    public InvestmentErrorResponse handleException(Exception e, HttpServletRequest request) {
        log.error("errorCode : {}, uri : {} , message{}", INTERNAL_SERVER_ERROR, request.getRequestURI(), e.getMessage());
        return InvestmentErrorResponse.builder()
                .errorCode(INTERNAL_SERVER_ERROR)
                .message(e.getMessage())
                .build();
    }
}
