package com.rgbrain.brianbot.domain.brian.infrastructure.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonTypeName;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@JsonTypeName("temperatures")
public class ResponsePrevisaoTemperatura extends ResponsePrevisao {
    private List<Temperature> temperatures;

    @Getter
    public static class Temperature {
        private String date;
        private Integer value;
    }
}
