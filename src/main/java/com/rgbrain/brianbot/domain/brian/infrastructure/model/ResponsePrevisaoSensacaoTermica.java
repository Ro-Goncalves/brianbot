package com.rgbrain.brianbot.domain.brian.infrastructure.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonTypeName;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@JsonTypeName("thermalSensations")
public class ResponsePrevisaoSensacaoTermica extends ResponsePrevisao {
    private List<ThermalSensation> thermalSensations;

    @Getter
    public static class ThermalSensation {
        private String date;
        private Integer value;
    }

}
