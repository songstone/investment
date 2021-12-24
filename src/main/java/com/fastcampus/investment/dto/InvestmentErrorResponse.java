package com.fastcampus.investment.dto;


import com.fastcampus.investment.exception.InvestmentErrorCode;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvestmentErrorResponse {
    private InvestmentErrorCode errorCode;
    private String message;

}
