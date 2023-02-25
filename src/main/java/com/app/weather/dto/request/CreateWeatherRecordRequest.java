package com.app.weather.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateWeatherRecordRequest {
    private int cityId;
    private long time;
    private String data;
}
