package com.rgbrain.brianbot.domain.brian.infrastructure.model;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponsePrevisaoVento extends ResponsePrevisao {
    private List<Wind> winds;

    @Getter
    public static class Wind extends DadoTemporal {
        private Integer velocity;
        private Integer direction;
        private String directionSymbol;

        @Override
        public Integer getValue() {
            return velocity;
        }
    }
}
