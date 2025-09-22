package com.example.demo.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LookupServiceResponse {

    private CreditDataResponsePersonalDetails creditdataResponsePersonalDetails;

    private CreditDataResponseDebt creditdataResponseDebt;

    private CreditDataResponseAssertedIncome creditdataResponseAssertedIncome;
}
