package com.example.demo.config;

import com.example.demo.domain.Endpoint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class WebClientConfig {

    @Bean
    @Endpoint(Endpoint.APISystem.CREDITDATA)
    public WebClient creditdataEndpoint(@Value("${creditdata.ingress.uri}") String creditdataUri) {
        return WebClient.builder()
                .baseUrl(creditdataUri)
                .build();
    }
}
