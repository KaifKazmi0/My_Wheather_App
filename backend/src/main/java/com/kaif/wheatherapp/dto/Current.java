package com.kaif.wheatherapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Current{
    public Integer last_updated_epoch;
    public String last_updated;
    public Double temp_c;
    public Double temp_f;
    public Integer is_day;
    public Condition condition;
    public Double wind_mph;
    public Double wind_kph;
    public Integer wind_degree;
    public String wind_dir;
    public Double pressure_mb;
    public Double pressure_in;
    public Double precip_mm;
    public Double precip_in;
    public Integer humidity;
    public Integer cloud;
    public Double feelslike_c;
    public Double feelslike_f;
    public Double windchill_c;
    public Double windchill_f;
    public Double heatindex_c;
    public Double heatindex_f;
    public Double dewpoint_c;
    public Double dewpoint_f;
    public Double vis_km;
    public Double vis_miles;
    public Double uv;
    public Double gust_mph;
    public Double gust_kph;
    public Integer will_it_rain;
    public Integer chance_of_rain;
    public Integer will_it_snow;
    public Integer chance_of_snow;
    public Double short_rad;
    public Double diff_rad;
    public Double dni;
    public Double gti;
}
