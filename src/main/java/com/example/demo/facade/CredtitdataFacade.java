package com.example.demo.facade;

import com.example.demo.domain.CreditdataResponseAssertedIncome;
import com.example.demo.domain.CreditdataResponseDebt;
import com.example.demo.domain.CreditdataResponsePersonalDetails;
import com.example.demo.domain.Endpoint;
import com.example.demo.exception.ApiError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Service
@Slf4j
public class CredtitdataFacade {

    private final WebClient webClient;

    @Autowired
    public CredtitdataFacade(@Endpoint(Endpoint.APISystem.CREDITDATA) WebClient webClient) {
        this.webClient = webClient;
    }

    public CreditdataResponsePersonalDetails getCreditdataPersonalDetails(String ssn) {

        try {
            return webClient
                    .get()
                    .uri("/api/credit-data/personal-details/{ssn}", ssn)
                    .retrieve()
                    .bodyToMono(CreditdataResponsePersonalDetails.class)
                    .block();
        } catch (WebClientResponseException e) {
            log.error("getCreditdataPersonalDetails WebClientResponseException:" + e);
            throw new ApiError(e.getStatusCode(), e.getMessage());
        }
    }

    public CreditdataResponseDebt getCreditdataDebt(String ssn) {

        try {
            return webClient
                    .get()
                    .uri("/api/credit-data/debt/{ssn}", ssn)
                    .retrieve()
                    .bodyToMono(CreditdataResponseDebt.class)
                    .block();
        } catch (WebClientResponseException e) {
            log.error("getCreditdataDebt WebClientResponseException:" + e);
            throw new ApiError(e.getStatusCode(), e.getMessage());
        }
    }

    public CreditdataResponseAssertedIncome getCreditdataAssertedIncome(String ssn) {

        try {
            return webClient
                    .get()
                    .uri("/api/credit-data/assessed-income/{ssn}", ssn)
                    .retrieve()
                    .bodyToMono(CreditdataResponseAssertedIncome.class)
                    .block();
        } catch (WebClientResponseException e) {
            log.error("getCreditdataAssertedIncome WebClientResponseException:" + e);
            throw new ApiError(e.getStatusCode(), e.getMessage());
        }
    }

}
