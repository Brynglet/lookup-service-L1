
package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CreditDataResponseAssertedIncome {

    @JsonProperty("assessed_income")
    private Integer assertedIncome;
}
