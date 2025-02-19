package com.rgbrain.brianbot.domain.brian.infrastructure.model;

// import java.time.LocalDateTime;
import java.util.List;

// import com.fasterxml.jackson.annotation.JsonFormat;
// import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@Getter
public class ResponsePrevisaoUmidade {
    private Long id;
    private String name;
    private String state;
    private String country;
    private List<Humidity> humidities;

    @Getter
    public static class Humidity {
        // @JsonProperty("date")
        // @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private String date;
        private Integer value;
    }

}
