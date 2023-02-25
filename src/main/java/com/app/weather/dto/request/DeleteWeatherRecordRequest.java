package com.app.weather.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeleteWeatherRecordRequest {
    private int id;

}
