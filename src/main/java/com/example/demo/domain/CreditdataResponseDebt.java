
package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class CreditdataResponseDebt {

    @JsonProperty("balance_of_debt")
    private Integer balanceOfDebt;

    @JsonProperty("complaints")
    private Boolean complaints;

}
