package com.rgbrain.brianbot.domain.brian.infrastructure.model.reponse;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponsePrevisaoSensacaoTermica extends ResponsePrevisao {
    private List<ThermalSensation> thermalSensations;
    
    public static class ThermalSensation extends ResponseDadoTemporal {

    }
}
