package com.app.weather.dto.openweather;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OpenWeatherResponseDTO {

    private Coord coord;
    private List<Weather> weather;
    private String base;
    private Main main;
    private int visibility;
    private Wind wind;
    private Rain rain;
    private Clouds clouds;
    private int dt;
    private Sys sys;
    private int timezone;
    private int id;
    private String name;
    private int cod;
    private String message;

}
