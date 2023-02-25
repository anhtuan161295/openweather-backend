package com.app.weather.service;

import com.google.gson.Gson;
import com.app.weather.config.OpenWeatherConfig;
import com.app.weather.dto.openweather.OpenWeatherResponseDTO;
import com.app.weather.dto.request.CreateWeatherRecordRequest;
import com.app.weather.dto.request.DeleteWeatherRecordRequest;
import com.app.weather.dto.request.GetWeatherRequest;
import com.app.weather.dto.request.UpdateWeatherRecordRequest;
import com.app.weather.dto.response.CreateWeatherRecordResponse;
import com.app.weather.dto.response.DeleteWeatherRecordResponse;
import com.app.weather.dto.response.GetWeatherResponse;
import com.app.weather.dto.response.UpdateWeatherRecordResponse;
import com.app.weather.entity.Weather;
import com.app.weather.feign.OpenWeatherFeignClient;
import com.app.weather.repository.WeatherRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.ZoneId;

@Service
@AllArgsConstructor
public class WeatherService {

    private OpenWeatherFeignClient openWeatherFeignClient;
    private OpenWeatherConfig openWeatherConfig;
    private static final Gson GSON = new Gson();
    private WeatherRepository weatherRepository;

    /**
     * This method will call the OpenWeather API and return the response.
     *
     * @param cityName city name.
     * @return OpenWeather response.
     */
    public OpenWeatherResponseDTO searchTodayWeatherByCity(String cityName) {
        String responseStr = openWeatherFeignClient.getWeatherByCityName(cityName, openWeatherConfig.getApi().getKey());
        OpenWeatherResponseDTO openWeatherResponseDTO = GSON.fromJson(responseStr, OpenWeatherResponseDTO.class);
        return openWeatherResponseDTO;
    }

    /**
     * This method will get the historical data in database and filter using city id and time.
     * If cityId is -1, the query will return results from all cities.
     *
     * @param request api request.
     * @return api response.
     */
    public GetWeatherResponse getWeatherRecords(GetWeatherRequest request) {
        GetWeatherResponse response = GetWeatherResponse.builder().build();

        Pageable pageable = PageRequest.of(request.getPage() - 1, request.getSize());
        int cityId = request.getCityId();
        long startTime = request.getFrom().atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long endTime = request.getTo().atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
        Page<Weather> weathers = Page.empty();
        if (request.getCityId() == -1) { // no city selected
            weathers = weatherRepository.findAllByTimeBetween(startTime, endTime, pageable);
        } else {
            weathers = weatherRepository.findAllByCityIdAndTimeBetween(cityId, startTime, endTime, pageable);
        }

        response.setData(weathers.toList());
        return response;
    }

    /**
     * This method will create a weather record.
     * The request fields such as city id, data will be validated.
     *
     * @param request api request.
     * @return api response.
     */
    public CreateWeatherRecordResponse createWeatherRecord(CreateWeatherRecordRequest request) {
        CreateWeatherRecordResponse response = CreateWeatherRecordResponse.builder().build();
        if (request.getCityId() == 0) {
            response.setCode(HttpStatus.BAD_REQUEST.value());
            response.setMessage("Invalid city id");
            return response;
        }

        if (StringUtils.isBlank(request.getData())) {
            response.setCode(HttpStatus.BAD_REQUEST.value());
            response.setMessage("Invalid data");
            return response;
        }

        Weather weather = Weather.builder().build();
        weather.setCityId(request.getCityId());
        weather.setTime(request.getTime());
        weather.setData(request.getData());
        weatherRepository.save(weather);
        return response;
    }

    /**
     * This method will update a weather record.
     * The request fields such as city id, data will be validated.
     * If the record is not existing, new record will be saved into database.
     *
     * @param request api request.
     * @return api response.
     */
    public UpdateWeatherRecordResponse updateWeatherRecord(UpdateWeatherRecordRequest request) {
        UpdateWeatherRecordResponse response = UpdateWeatherRecordResponse.builder().build();
        if (request.getCityId() == 0) {
            response.setCode(HttpStatus.BAD_REQUEST.value());
            response.setMessage("Invalid city id");
            return response;
        }

        if (StringUtils.isBlank(request.getData())) {
            response.setCode(HttpStatus.BAD_REQUEST.value());
            response.setMessage("Invalid data");
            return response;
        }

        Weather weather = weatherRepository.findById(request.getId()).orElse(new Weather());
        weather.setCityId(request.getCityId());
        weather.setTime(request.getTime());
        weather.setData(request.getData());
        weatherRepository.save(weather);
        return response;
    }

    /**
     * This method will delete the record based on record id from api request.
     *
     * @param request api request.
     * @return api response.
     */
    public DeleteWeatherRecordResponse deleteWeatherRecord(DeleteWeatherRecordRequest request) {
        Weather weather = weatherRepository.findById(request.getId()).orElse(null);
        if (weather != null) {
            weatherRepository.deleteById(weather.getId());
        }

        DeleteWeatherRecordResponse response = DeleteWeatherRecordResponse.builder().build();
        return response;
    }

}
