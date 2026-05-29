package com.kaif.wheatherapp.service;

import com.kaif.wheatherapp.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class WeatherService {

    @Autowired
    private RestTemplate template;

    @Value("${weather.api.key}")
    private String apikey;

    @Value("${weather.api.url}")
    private String apiUrl;

    @Value("${weather.api.forecast.url}")
    private String forecastUrl;

    public String test(){
        return "test successful";
    }


    @CachePut(value = "weather", key = "#city")
    public WeatherResponse refreshData(String city) {
        log.info("Calling external weather API for {}", city);

        String url = apiUrl + "?key=" + apikey + "&q=" + city;

        Root response = template.getForObject(url, Root.class);

        if(response == null){
            throw new RuntimeException("Failed to fetch weather data");
        }

        WeatherResponse weatherResponse = new WeatherResponse();
        weatherResponse.setCity(response.getLocation().getName());
        weatherResponse.setTemp(response.getCurrent().getTemp_c());
        weatherResponse.setCondition(response.getCurrent().getCondition().getText());
        weatherResponse.setRegion(response.getLocation().getRegion());
        weatherResponse.setCountry(response.getLocation().getCountry());

        return weatherResponse;
    }

    @Cacheable(value = "weather",key = "#city")
    public WeatherResponse getData(String city) {
        String url = apiUrl+"?key="+apikey+"&q="+city;
        Root response = template.getForObject(url, Root.class);
        if(response==null){
            throw new RuntimeException("Failed to fetch weather data");
        }
        WeatherResponse weatherResponse = new WeatherResponse();
        weatherResponse.setCity(response.getLocation().getName());
        weatherResponse.setTemp(response.getCurrent().getTemp_c());
        weatherResponse.setCondition(response.getCurrent().getCondition().getText());
        weatherResponse.setRegion(response.getLocation().getRegion());
        weatherResponse.setCountry(response.getLocation().getCountry());
        return weatherResponse;
    }

    public ResponseForecast getForecast(String city,int day){

        ResponseForecast responseForecast = new ResponseForecast();
        WeatherResponse data = getData(city);
        responseForecast.setWeatherResponse(data);

        String myUrl = forecastUrl+"?key="+apikey+"&q="+city+"&days="+day;
        Root response = template.getForObject(myUrl, Root.class);
        if(response==null){
            throw new RuntimeException("Failed to fetch weather data");
        }
        Forecast forecast = response.getForecast();
        ArrayList<Forecastday> forecastday = forecast.getForecastday();
        List<DayTemp> dayTempList = new ArrayList<>();
        for(Forecastday myday: forecastday){
            DayTemp dayTemp = new DayTemp();
            dayTemp.setAvgTemp(myday.getDay().getAvgtemp_c());
            dayTemp.setMaxTemp(myday.getDay().getMaxtemp_c());
            dayTemp.setMinTemp(myday.getDay().getMintemp_c());
            dayTemp.setDate(myday.getDate());
            dayTempList.add(dayTemp);
        }

        responseForecast.setDayTemps(dayTempList);
        return responseForecast;

    }


}
