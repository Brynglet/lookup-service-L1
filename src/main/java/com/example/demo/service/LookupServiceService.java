package com.example.demo.service;

import com.example.demo.domain.CreditdataResponseAssertedIncome;
import com.example.demo.domain.CreditdataResponseDebt;
import com.example.demo.domain.CreditdataResponsePersonalDetails;
import com.example.demo.exception.ApiError;
import com.example.demo.facade.CredtitdataFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@Service
public class LookupServiceService {

    private final CredtitdataFacade credtitdataFacade;

    @Autowired
    public LookupServiceService(CredtitdataFacade credtitdataFacade) {
        this.credtitdataFacade = credtitdataFacade;
    }

    public Map<String, Object> getLookupServiceResponse(String ssn) {

        log.info("getLookupServiceResponse {}", ssn);

        try {

            CreditdataResponsePersonalDetails personalDetails =
                    credtitdataFacade.getCreditdataPersonalDetails(ssn);

            CreditdataResponseDebt debt =
                    credtitdataFacade.getCreditdataDebt(ssn);

            CreditdataResponseAssertedIncome income =
                    credtitdataFacade.getCreditdataAssertedIncome(ssn);

            Map<String, Object> valueMap = new LinkedHashMap<>();
            valueMap.put("first_name", personalDetails.getFirstName());
            valueMap.put("last_name", personalDetails.getLastName());
            valueMap.put("address", personalDetails.getAddress());
            valueMap.put("assessed_income", income.getAssertedIncome());
            valueMap.put("balance_of_debt", debt.getBalanceOfDebt());
            valueMap.put("complaints", debt.getComplaints());

            // Build the outer map with dynamic key
            String dynamicKey = "CreditData" + personalDetails.getFirstName();
            Map<String, Object> response = new HashMap<>();
            response.put(dynamicKey, Map.of("value", valueMap));

            log.info("getLookupServiceResponse response ok for {}", ssn);

            return response;

        }  catch (WebClientResponseException e) {

            log.error("WebClientResponseException:" + e);

            throw new ApiError(e.getStatusCode(), e.getMessage());

        }

    }

}
