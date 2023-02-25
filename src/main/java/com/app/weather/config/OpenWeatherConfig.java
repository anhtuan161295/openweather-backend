package com.app.weather.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "open-weather")
@Data
public class OpenWeatherConfig {

    private ApiConfig api;

    @Data
    public static class ApiConfig {
        private String key;
    }

}
