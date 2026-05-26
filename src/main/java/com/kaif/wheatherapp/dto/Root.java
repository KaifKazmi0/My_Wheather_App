package com.kaif.wheatherapp.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Root{
    public Location location;
    public Current current;
    public Forecast forecast;
}
