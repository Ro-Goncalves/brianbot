package com.rgbrain.brianbot.domain.brian.infrastructure.model;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponsePrevisaoTemperatura extends ResponsePrevisao {
    private List<Temperature> temperatures;
    
    public static class Temperature extends DadoTemporal {

    }
}
