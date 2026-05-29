package com.kaif.wheatherapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DayTemp {
    private String date;
    private Double minTemp;
    private Double avgTemp;
    private Double maxTemp;
}
