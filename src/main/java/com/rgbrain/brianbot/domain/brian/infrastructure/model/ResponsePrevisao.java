package com.rgbrain.brianbot.domain.brian.infrastructure.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@JsonSubTypes({
        @JsonSubTypes.Type(value = ResponsePrevisaoPrecipitacao.class, name = "precipitations"),
        @JsonSubTypes.Type(value = ResponsePrevisaoPrecipitacao.class, name = "temperatures")
})
public class ResponsePrevisao {
    private Integer id;
    private String name;
    private String state;
    private String country;
}
