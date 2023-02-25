package com.app.weather.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = Weather.WEATHER_TABLE_NAME)
public class Weather {

    public static final String WEATHER_TABLE_NAME = "weather";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private int cityId;

    private long time;

    @Column(name = "data", columnDefinition = "TEXT")
    private String data;

}
