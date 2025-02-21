package com.rgbrain.brianbot.domain.brian;

public class AdvisorDados {

    public static String exemploResponsePrevisaoClima() {
        return  """
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
        return  """
                    {
                        "id": 6997,
                        "name": "Londrina",
                        "state": "PR",
                        "country": "BR",
                        "humidities": [
                            {
                                "date": "2025-02-19 13:00:00",
                                "value": 79
                            },
                            {
                                "date": "2025-02-19 14:00:00",
                                "value": 71
                            },
                            {
                                "date": "2025-02-19 15:00:00",
                                "value": 65
                            },
                            {
                                "date": "2025-02-19 16:00:00",
                                "value": 60
                            },
                            {
                                "date": "2025-02-19 17:00:00",
                                "value": 56
                            },
                            {
                                "date": "2025-02-19 18:00:00",
                                "value": 53
                            },
                            {
                                "date": "2025-02-19 19:00:00",
                                "value": 56
                            }
                        ]
                    }
                """;
    }

    public static String exemploResponsePrevisaoPrecipitacao() {
        return """
                    {
                        "id": 6997,
                        "name": "Londrina",
                        "state": "PR",
                        "country": "BR",
                        "precipitations": [
                            {
                                "date": "2025-02-20 22:00:00",
                                "value": 0
                            },
                            {
                                "date": "2025-02-20 23:00:00",
                                "value": 0
                            },
                            {
                                "date": "2025-02-21 00:00:00",
                                "value": 0
                            },
                            {
                                "date": "2025-02-21 01:00:00",
                                "value": 0
                            },
                            {
                                "date": "2025-02-21 02:00:00",
                                "value": 0
                            }
                        ]
                    }
                """;
    }

}
