package com.kaif.wheatherapp.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WeatherResponse {
    private String city;
    private String region;
    private String country;
    private String condition;
    private Double Temp;
}
