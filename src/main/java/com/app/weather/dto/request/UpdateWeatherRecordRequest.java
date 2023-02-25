package com.app.weather.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateWeatherRecordRequest {
    private int id;
    private int cityId;
    private long time;
    private String data;
}
