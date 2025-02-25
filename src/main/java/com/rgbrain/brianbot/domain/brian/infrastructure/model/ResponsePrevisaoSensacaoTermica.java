package com.rgbrain.brianbot.domain.brian.infrastructure.model;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponsePrevisaoSensacaoTermica extends ResponsePrevisao {
    private List<ThermalSensation> thermalSensations;
    
    public static class ThermalSensation extends DadoTemporal {

    }
}
