package com.rgbrain.brianbot.domain.brian.infrastructure.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonTypeName;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@JsonTypeName("precipitations")
public class ResponsePrevisaoPrecipitacao extends ResponsePrevisao {
    private List<Precipitations> precipitations;

    @Getter
    public static class Precipitations {
        private String date;
        private Integer value;
    }
}
