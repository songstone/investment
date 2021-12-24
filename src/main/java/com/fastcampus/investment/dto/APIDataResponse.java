package com.fastcampus.investment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class APIDataResponse<T> {
    private T data;

    public static <T> APIDataResponse of(T data) {
        return new APIDataResponse(data);
    }
}
