package com.app.weather.dto.openweather;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Main {

    private float temp;
    private float feelsLike;
    private float tempMin;
    private float tempMax;
    private int pressure;
    private int humidity;
    private int seaLevel;
    private int grndLevel;
}
