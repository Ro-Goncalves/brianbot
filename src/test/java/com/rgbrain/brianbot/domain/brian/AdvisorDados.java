package com.rgbrain.brianbot.domain.brian;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rgbrain.brianbot.domain.brian.infrastructure.model.reponse.ResponsePrevisaoPrecipitacao;
import com.rgbrain.brianbot.domain.brian.infrastructure.model.reponse.ResponsePrevisaoSensacaoTermica;
import com.rgbrain.brianbot.domain.brian.infrastructure.model.reponse.ResponsePrevisaoTemperatura;
import com.rgbrain.brianbot.domain.brian.infrastructure.model.reponse.ResponsePrevisaoUmidade;

public class AdvisorDados {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static String exemploResponsePrevisaoClima() {
        return """
                    {
                        "id": 6997,
                        "name": "Londrina",
                        "state": "PR",
                        "country": "BR",
                        "data": [
                            {
                                "date": "2025-02-18",
                                "datebr": "18/02/2025",
                                "humidity": {
                                    "min": 54,
                                    "max": 85
                                },
                                "rain": {
                                    "precipitation": 6
                                },
                                "wind": {
                                    "velocity": 6,
                                    "direction": "NW",
                                    "direction_degrees": 274
                                },
                                "thermal_sensation": {
                                    "min": 22,
                                    "max": 35
                                },
                                "temperature": {
                                    "min": 24,
                                    "max": 33
                                }
                            },
                            {
                                "date": "2025-02-19",
                                "datebr": "19/02/2025",
                                "humidity": {
                                    "min": 63,
                                    "max": 99
                                },
                                "rain": {
                                    "precipitation": 11
                                },
                                "wind": {
                                    "velocity": 7,
                                    "direction": "E",
                                    "direction_degrees": 124
                                },
                                "thermal_sensation": {
                                    "min": 21,
                                    "max": 36
                                },
                                "temperature": {
                                    "min": 21,
                                    "max": 31
                                }
                            }
                        ]
                    }
                """;
    }

    public static String exemploResponsePrevisaoUmidade() {
        StringBuilder jsonString = exemploResponsePrevisao();
        jsonString.append("    \"humidities\": [\n");
        adicionarExemploResponseDadosTemporais(jsonString);

        return jsonString.toString();
    }

    public static String exemploResponsePrevisaoPrecipitacao() {
        StringBuilder jsonString = exemploResponsePrevisao();
        jsonString.append("    \"precipitations\": [\n");
        adicionarExemploResponseDadosTemporais(jsonString);

        return jsonString.toString();
    }

    public static String exemploResponsePrevisaoTemperatura() {
        StringBuilder jsonString = exemploResponsePrevisao();
        jsonString.append("    \"temperatures\": [\n");
        adicionarExemploResponseDadosTemporais(jsonString);

        return jsonString.toString();
    }

    public static String exemploResponsePrevisaoSensacaoTermica() {
        StringBuilder jsonString = exemploResponsePrevisao();
        jsonString.append("    \"thermalSensations\": [\n");
        adicionarExemploResponseDadosTemporais(jsonString);

        return jsonString.toString();
    }

    public static String exemploResponsePrevisaoVento() {
        StringBuilder jsonString = exemploResponsePrevisao();
        jsonString.append("    \"winds\": [\n");

        List<String> datasTeste = datasParaTeste();

        int[] velocity = valoresParaTeste();
        String[] directionSymbol = {
                "N", "NNE", "NE", "ENE", "E",
                "ESE", "SE", "SSE", "S", "SSW",
                "SW", "WSW", "W", "WNW", "NW" };

        int[] direction = {
                0, 22, 45, 67, 90,
                112, 135, 157, 180, 202,
                225, 247, 270, 292, 315 };

        for (int i = 0; i < datasTeste.size(); i++) {
            jsonString.append("        {\n");
            jsonString.append("            \"date\": \"").append(datasTeste.get(i)).append("\",\n");
            jsonString.append("            \"velocity\": ").append(velocity[i]).append("\n");
            jsonString.append("            \"direction\": ").append(direction[i]).append("\n");
            jsonString.append("            \"directionSymbol\": \"").append(directionSymbol[i]).append("\"\n");
            jsonString.append("        }");
            if (i < datasTeste.size() - 1) {
                jsonString.append(",");
            }
            jsonString.append("\n");
        }

        jsonString.append("    ]\n");
        jsonString.append("}");

        return jsonString.toString();
    }

    public static ResponsePrevisaoTemperatura exemploClasseResponsePrevisaoTemperatura() {
        try {
            return objectMapper.readValue(AdvisorDados.exemploResponsePrevisaoTemperatura(),
                    ResponsePrevisaoTemperatura.class);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    public static ResponsePrevisaoUmidade exemploClasseResponsePrevisaoUmidade() {
        try {
            return objectMapper.readValue(AdvisorDados.exemploResponsePrevisaoUmidade(),
                    ResponsePrevisaoUmidade.class);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    public static ResponsePrevisaoPrecipitacao exemploClasseResponsePrevisaoPrecipitacao() {
        try {
            return objectMapper.readValue(AdvisorDados.exemploResponsePrevisaoPrecipitacao(),
                    ResponsePrevisaoPrecipitacao.class);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    public static ResponsePrevisaoSensacaoTermica exemploClasseResponsePrevisaoSensacaoTermica() {
        try {
            return objectMapper.readValue(AdvisorDados.exemploResponsePrevisaoSensacaoTermica(),
                    ResponsePrevisaoSensacaoTermica.class);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    private static List<String> datasParaTeste() {
        List<String> datasTeste = new ArrayList<>();
        var dataAtual = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        var horaInicial = 6;
        var horaFinal = 20;

        for (int i = horaInicial; i <= horaFinal; i++) {
            LocalDateTime date = dataAtual.withHour(i).withMinute(0).withSecond(0);
            datasTeste.add(date.format(formatter));
        }

        return datasTeste;
    }

    private static int[] valoresParaTeste() {
        int[] valoresTeste = { 79, 71, 65, 60, 56, 53, 56, 60, 65, 71, 79, 85, 85, 79, 71 };
        return valoresTeste;
    }

    private static StringBuilder exemploResponsePrevisao() {
        StringBuilder jsonString = new StringBuilder();
        jsonString.append("{\n");
        jsonString.append("    \"id\": 6997,\n");
        jsonString.append("    \"name\": \"Londrina\",\n");
        jsonString.append("    \"state\": \"PR\",\n");
        jsonString.append("    \"country\": \"BR\",\n");

        return jsonString;
    }

    private static void adicionarExemploResponseDadosTemporais(StringBuilder jsonString) {
        List<String> datasTeste = datasParaTeste();

        int[] valoresTeste = valoresParaTeste();

        for (int i = 0; i < datasTeste.size(); i++) {
            jsonString.append("        {\n");
            jsonString.append("            \"date\": \"").append(datasTeste.get(i)).append("\",\n");
            jsonString.append("            \"value\": ").append(valoresTeste[i]).append("\n");
            jsonString.append("        }");
            if (i < datasTeste.size() - 1) {
                jsonString.append(",");
            }
            jsonString.append("\n");
        }

        jsonString.append("    ]\n");
        jsonString.append("}");
    }
}
