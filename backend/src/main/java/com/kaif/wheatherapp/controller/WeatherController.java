package com.kaif.wheatherapp.controller;

import com.kaif.wheatherapp.dto.ResponseForecast;
import com.kaif.wheatherapp.dto.Root;
import com.kaif.wheatherapp.dto.WeatherResponse;
import com.kaif.wheatherapp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/test")
    public String test(){
        return weatherService.test();
    }

    @GetMapping("/{city}")
    public WeatherResponse getWeatherData(@PathVariable String city){
        return weatherService.getData(city);
    }

    @GetMapping("/forecast")
    public ResponseForecast getForecast(@RequestParam String city,@RequestParam int day){
        return weatherService.getForecast(city,day);
    }
}
