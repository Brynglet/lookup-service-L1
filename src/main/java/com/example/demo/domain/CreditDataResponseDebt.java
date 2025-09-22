
package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CreditDataResponseDebt {

    @JsonProperty("balance_of_debt")
    private Integer balanceOfDebt;

    @JsonProperty("complaints")
    private Boolean complaints;
}
