package com.kaif.wheatherapp.cache;


import com.kaif.wheatherapp.service.WeatherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class WeatherCacheCronJob {


    private final WeatherService weatherService;

    @Scheduled(cron = "0 */10 * * * *")
    public void warmTopCitiesCache() {

        String[] cities = {
                "Mumbai",
                "Delhi",
                "Bangalore",
                "Pune",
                "Chennai",
                "Kolkata"
        };

        for (String city : cities) {
            try {
                weatherService.refreshData(city);
                log.info("Cache refreshed for {}", city);
            } catch (Exception e) {
                log.error("Failed to refresh cache for {}", city, e);
            }
        }
    }
}
