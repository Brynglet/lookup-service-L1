package com.example.demo.service;

import com.example.demo.domain.CreditdataResponseAssertedIncome;
import com.example.demo.domain.CreditdataResponseDebt;
import com.example.demo.domain.CreditdataResponsePersonalDetails;
import com.example.demo.domain.LookupServiceResponse;
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

    public LookupServiceResponse getLookupServiceResponse(String ssn) {

        LookupServiceResponse lookupServiceResponse = new LookupServiceResponse();

        CreditdataResponsePersonalDetails CreditdataResponsePersonalDetails =
                credtitdataFacade.getCreditdataPersonalDetails(ssn);

        CreditdataResponseDebt creditdataResponseDebt =
                credtitdataFacade.getCreditdataDebt(ssn);

        CreditdataResponseAssertedIncome creditdataResponseAssertedIncome =
                credtitdataFacade.getCreditdataAssertedIncome(ssn);

        lookupServiceResponse.setCreditdataResponseDebt(creditdataResponseDebt);
        lookupServiceResponse.setCreditdataResponseAssertedIncome(creditdataResponseAssertedIncome);
        lookupServiceResponse.setCreditdataResponsePersonalDetails(CreditdataResponsePersonalDetails);

        return lookupServiceResponse;

    }

}
