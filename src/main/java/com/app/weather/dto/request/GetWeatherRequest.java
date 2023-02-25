package com.app.weather.dto.request;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class GetWeatherRequest {
    private int page;
    private int size;
    private LocalDate from;
    private LocalDate to;
    private int cityId;
}
