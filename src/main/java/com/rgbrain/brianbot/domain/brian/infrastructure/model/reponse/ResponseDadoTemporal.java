package com.rgbrain.brianbot.domain.brian.infrastructure.model.reponse;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor 
public class ResponseDadoTemporal {
    private String date;
    private Integer value;

    public LocalDateTime getDateToLocaleDateTime() {
        return LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}