package com.example.demo.service;

import com.example.demo.domain.CreditdataResponseAssertedIncome;
import com.example.demo.domain.CreditdataResponseDebt;
import com.example.demo.domain.CreditdataResponsePersonalDetails;
import com.example.demo.domain.LookupServiceResponse;
import com.example.demo.facade.CredtitdataFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class LookupServiceService {

    private final CredtitdataFacade credtitdataFacade;

    @Autowired
    public LookupServiceService(CredtitdataFacade credtitdataFacade) {
        this.credtitdataFacade = credtitdataFacade;
    }

    public Map<String, Object> getLookupServiceResponse(String ssn) {

        CreditdataResponsePersonalDetails personalDetails =
                credtitdataFacade.getCreditdataPersonalDetails(ssn);

        CreditdataResponseDebt debt =
                credtitdataFacade.getCreditdataDebt(ssn);

        CreditdataResponseAssertedIncome income =
                credtitdataFacade.getCreditdataAssertedIncome(ssn);

        // Merge into a flat map for "value"
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

        return response;
    }

}
