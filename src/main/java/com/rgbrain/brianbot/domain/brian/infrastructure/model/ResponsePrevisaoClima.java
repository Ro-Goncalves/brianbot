package com.rgbrain.brianbot.domain.brian.infrastructure.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.Getter;

@Getter
public class ResponsePrevisaoClima {
    private int id;
    private String name;
    private String state;
    private String country;
    private List<DailyData> data;

    @Getter
    public static class DailyData {
        private String date;
        private String datebr;
        private Humidity humidity;
        private Rain rain;
        private Wind wind;
        @JsonAlias("thermal_sensation")
        private ThermalSensation thermalSensation;
        private Temperature temperature;
    }

    @Getter
    public static class Humidity {
        private int min;
        private int max; 
    }

    @Getter
    public static class Rain {
        private int precipitation;
    }
    
    @Getter
    public static class Wind {
        private int velocity;
        private String direction;
        @JsonAlias("direction_degrees")
        private int directionDegrees;
    }
        
    @Getter
    public static class ThermalSensation {
        private int min;
        private int max;
    }
    
    @Getter
    public static class Temperature {
        private int min;
        private int max;
    }
}





