package com.app.weather.dto.openweather;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Wind {

    public float speed;
    public int deg;
    public float gust;
}
