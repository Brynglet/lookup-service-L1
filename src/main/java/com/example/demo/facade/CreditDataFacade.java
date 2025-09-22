package com.example.demo.facade;

import com.example.demo.domain.CreditDataResponseAssertedIncome;
import com.example.demo.domain.CreditDataResponseDebt;
import com.example.demo.domain.CreditDataResponsePersonalDetails;
import com.example.demo.domain.Endpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Slf4j
public class CreditDataFacade {

    private final WebClient webClient;

    @Autowired
    public CreditDataFacade(@Endpoint(Endpoint.APISystem.CREDITDATA) WebClient webClient) {
        this.webClient = webClient;
    }

    public CreditDataResponsePersonalDetails getCreditDataPersonalDetails(String ssn) {

        return webClient
                .get()
                .uri("/api/credit-data/personal-details/{ssn}", ssn)
                .retrieve()
                .bodyToMono(CreditDataResponsePersonalDetails.class)
                .block();
    }

    public CreditDataResponseDebt getCreditDataDebt(String ssn) {

        return webClient
                .get()
                .uri("/api/credit-data/debt/{ssn}", ssn)
                .retrieve()
                .bodyToMono(CreditDataResponseDebt.class)
                .block();
    }

    public CreditDataResponseAssertedIncome getCreditDataAssertedIncome(String ssn) {

        return webClient
                .get()
                .uri("/api/credit-data/assessed-income/{ssn}", ssn)
                .retrieve()
                .bodyToMono(CreditDataResponseAssertedIncome.class)
                .block();
    }

}
