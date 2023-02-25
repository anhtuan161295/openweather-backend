package com.app.weather.repository;

import com.app.weather.entity.Weather;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface WeatherRepository extends JpaRepository<Weather, Integer> {
    Page<Weather> findAllByCityId(int cityId, Pageable pageable);

    Page<Weather> findAllByCityIdAndTimeBetween(int cityId, long startTime, long endTime, Pageable pageable);

    Page<Weather> findAllByTimeBetween(long startTime, long endTime, Pageable pageable);

}
