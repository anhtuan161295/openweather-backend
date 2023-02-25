package com.app.weather.dto.openweather;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Sys {

    private int type;
    private int id;
    private String country;
    private int sunrise;
    private int sunset;
}
