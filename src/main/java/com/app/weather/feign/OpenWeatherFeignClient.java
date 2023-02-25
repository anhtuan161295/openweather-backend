package com.app.weather.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "OpenWeatherApi", url = "http://api.openweathermap.org/data/2.5", decode404 = true)
public interface OpenWeatherFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/weather")
    String getWeatherByCityName(@RequestParam(name = "q") String cityName, @RequestParam String appId);

}
