package com.example.demo.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LookupServiceResponse {

    private CreditdataResponsePersonalDetails creditdataResponsePersonalDetails;

    private CreditdataResponseDebt creditdataResponseDebt;

    private CreditdataResponseAssertedIncome creditdataResponseAssertedIncome;

}
