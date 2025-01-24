package com.rgbrain.brianbot.infrastructure.configuracoes.domain;

import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class ConfiguracaoBrianBot {

    private static final String BASE_URL = "https://api.groq.com/openai";

	private static final String DEFAULT_MODEL = "llama-3.1-8b-instant";
    
    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
    
    @Bean
    ObjectMapper objectMapper(){
        return new ObjectMapper();
    }

    @Bean
    public OpenAiApi chatCompletionApi() {
        return new OpenAiApi(BASE_URL, System.getenv("GROQ_API_KEY"));
    }

    @Bean
    public OpenAiChatModel openAiClient(OpenAiApi openAiApi) {
        return new OpenAiChatModel(
            openAiApi, 
            OpenAiChatOptions.builder()
                .withModel(DEFAULT_MODEL)
                .withTemperature(0.85)
            .build());
    }
    
}
