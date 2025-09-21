package com.example.demo.service;

import com.example.demo.domain.CreditdataResponseAssertedIncome;
import com.example.demo.domain.CreditdataResponseDebt;
import com.example.demo.domain.CreditdataResponsePersonalDetails;
import com.example.demo.facade.CredtitdataFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LookupServiceService {

    private final CredtitdataFacade credtitdataFacade;

    @Autowired
    public LookupServiceService(CredtitdataFacade credtitdataFacade) {
        this.credtitdataFacade = credtitdataFacade;
    }

    public CreditdataResponsePersonalDetails getCreditdataPersonalDetails(String ssn) {
        return credtitdataFacade.getCreditdataPersonalDetails(ssn);
    }

    public CreditdataResponseDebt getCreditdataResponseDebt(String ssn) {
        return credtitdataFacade.getCreditdataDebt(ssn);
    }

    public CreditdataResponseAssertedIncome getCreditdataResponseAssertedIncome(String ssn) {
        return credtitdataFacade.getCreditdataAssertedIncome(ssn);
    }
}
