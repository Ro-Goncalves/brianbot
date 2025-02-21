package com.rgbrain.brianbot.domain.brian.infrastructure;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.rgbrain.brianbot.domain.brian.infrastructure.model.ResponsePrevisao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@JsonTypeName("winds")
public class ResponsePrevisaoVento extends ResponsePrevisao {
    private List<Wind> winds;

    @Getter
    public static class Wind {
        private String date;
        private Integer velocity;
        private Integer direction;
        private String directionSymbol;
    }
}
