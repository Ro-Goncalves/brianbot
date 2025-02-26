package com.rgbrain.brianbot.domain.brian.infrastructure.model.reponse;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponsePrevisaoPrecipitacao extends ResponsePrevisao {
    private List<Precipitation> precipitations;
    
    public static class Precipitation extends ResponseDadoTemporal {
       
    }
}
