package com.app.weather.dto.openweather;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Coord {

    private float lon;
    private float lat;

}
