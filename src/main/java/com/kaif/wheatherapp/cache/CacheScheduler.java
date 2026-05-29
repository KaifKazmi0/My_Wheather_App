package com.kaif.wheatherapp.cache;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
@EnableCaching
public class CacheScheduler {

    @Scheduled(fixedRate = 1000*60) // x mins
    @CacheEvict(value = "weather", allEntries = true)
    public void clearWeatherCache() {
        System.out.println("Weather cache cleared");
    }
}
