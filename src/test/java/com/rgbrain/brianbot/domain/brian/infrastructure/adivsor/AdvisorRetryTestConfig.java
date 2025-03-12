package com.rgbrain.brianbot.domain.brian.infrastructure.adivsor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;

@Configuration
@EnableRetry
class AdvisorRetryTestConfig {

    @Bean
    public AdivsorPrevisaoUmidade adivsorPrevisaoUmidade(AdvisorClient advisorClient) {
        return new AdivsorPrevisaoUmidade("http://teste.url", advisorClient);
    }
}
