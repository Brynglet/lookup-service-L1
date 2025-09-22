package com.example.demo.service;

import com.example.demo.domain.CreditDataResponseAssertedIncome;
import com.example.demo.domain.CreditDataResponseDebt;
import com.example.demo.domain.CreditDataResponsePersonalDetails;
import com.example.demo.exception.ApiError;
import com.example.demo.facade.CreditDataFacade;
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

    private final CreditDataFacade creditDataFacade;

    @Autowired
    public LookupServiceService(CreditDataFacade creditdataFacade) {
        this.creditDataFacade = creditdataFacade;
    }

    public Map<String, Object> getLookupServiceResponse(String ssn) {

        log.info("getLookupServiceResponse {}", ssn);

        try {

            CreditDataResponsePersonalDetails creditDataResponsePersonalDetails =
                    creditDataFacade.getCreditDataPersonalDetails(ssn);

            CreditDataResponseDebt creditDataResponseDebt =
                    creditDataFacade.getCreditDataDebt(ssn);

            CreditDataResponseAssertedIncome creditDataResponseAssertedIncome =
                    creditDataFacade.getCreditDataAssertedIncome(ssn);

            Map<String, Object> valueMap = new LinkedHashMap<>();
            valueMap.put("first_name", creditDataResponsePersonalDetails.getFirstName());
            valueMap.put("last_name", creditDataResponsePersonalDetails.getLastName());
            valueMap.put("address", creditDataResponsePersonalDetails.getAddress());
            valueMap.put("assessed_income", creditDataResponseAssertedIncome.getAssertedIncome());
            valueMap.put("balance_of_debt", creditDataResponseDebt.getBalanceOfDebt());
            valueMap.put("complaints", creditDataResponseDebt.getComplaints());

            // Build the outer map with dynamic key
            String dynamicKey = "CreditData" + creditDataResponsePersonalDetails.getFirstName();
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
