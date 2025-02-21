package com.rgbrain.brianbot.domain.brian.infrastructure.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@JsonSubTypes({
        @JsonSubTypes.Type(value = ResponsePrevisaoPrecipitacao.class, name = "precipitations")
})
public class ResponsePrevisao {
    private int id;
    private String name;
    private String state;
    private String country;
}
