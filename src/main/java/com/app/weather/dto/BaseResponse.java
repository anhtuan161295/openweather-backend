package com.app.weather.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse {
    @Builder.Default
    private int code = 200;
    @Builder.Default
    private String message = "";
    @Builder.Default
    private Object data = null;
}
