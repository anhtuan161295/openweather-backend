package com.app.weather.controller;

import com.app.weather.dto.openweather.OpenWeatherResponseDTO;
import com.app.weather.dto.request.CreateWeatherRecordRequest;
import com.app.weather.dto.request.DeleteWeatherRecordRequest;
import com.app.weather.dto.request.GetWeatherRequest;
import com.app.weather.dto.request.UpdateWeatherRecordRequest;
import com.app.weather.dto.response.*;
import com.app.weather.service.WeatherService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/weather")
@AllArgsConstructor
@Slf4j
public class WeatherController {

    private WeatherService weatherService;

    @GetMapping("")
    public Map<String, Object> getWeathers() {
        return Collections.singletonMap("message", "Welcome to Openweather Backend");
    }

    @GetMapping(value = "/search-today-weather")
    public SearchTodayWeatherResponse searchTodayWeatherByCity(@RequestParam String cityName) {
        OpenWeatherResponseDTO openWeatherResponseDTO = weatherService.searchTodayWeatherByCity(cityName);
        //
        SearchTodayWeatherResponse responseDTO = SearchTodayWeatherResponse.builder().build();
        if (openWeatherResponseDTO.getCod() != 200) {
            responseDTO.setCode(openWeatherResponseDTO.getCod());
            responseDTO.setMessage(openWeatherResponseDTO.getMessage());
            return responseDTO;
        }

        responseDTO.setData(openWeatherResponseDTO);
        return responseDTO;
    }

    @GetMapping("/records")
    public GetWeatherResponse getWeatherRecords(
            @RequestParam(value = "from") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fromDate,
            @RequestParam("to") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate toDate,
            @RequestParam(value = "cityId", defaultValue = "-1") int cityId,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size) {

        GetWeatherRequest request = GetWeatherRequest.builder()
                .from(fromDate)
                .to(toDate)
                .cityId(cityId)
                .page(page)
                .size(size)
                .build();
        log.info("Get weather records - {}", request);

        GetWeatherResponse response = weatherService.getWeatherRecords(request);
        log.info("Get weather records response - {}", response);
        return response;
    }

    @PostMapping("/record")
    public CreateWeatherRecordResponse saveWeatherRecord(@RequestBody CreateWeatherRecordRequest request) {
        log.info("Save new weather record - {}", request);
        CreateWeatherRecordResponse response = weatherService.createWeatherRecord(request);
        log.info("Save new weather record response - {}", response);
        return response;
    }

    @PutMapping("/record/{id}")
    public UpdateWeatherRecordResponse updateWeatherRecord(@PathVariable Integer id, @RequestBody UpdateWeatherRecordRequest request) {
        request.setId(id);
        log.info("Update weather record - {}", request);
        UpdateWeatherRecordResponse response = weatherService.updateWeatherRecord(request);
        log.info("Update weather record response - {}", response);
        return response;
    }

    @DeleteMapping("/record/{id}")
    public DeleteWeatherRecordResponse deleteWeatherRecord(@PathVariable Integer id) {
        DeleteWeatherRecordRequest request = DeleteWeatherRecordRequest.builder().id(id).build();
        log.info("Delete weather record - {}", request);
        DeleteWeatherRecordResponse response = weatherService.deleteWeatherRecord(request);
        log.info("Delete weather record response - {}", response);
        return response;
    }

}