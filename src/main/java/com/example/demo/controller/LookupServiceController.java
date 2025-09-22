package com.example.demo.controller;

import com.example.demo.domain.CreditdataResponseAssertedIncome;
import com.example.demo.domain.CreditdataResponseDebt;
import com.example.demo.domain.CreditdataResponsePersonalDetails;
import com.example.demo.service.LookupServiceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;

@Slf4j
@RestController
public class LookupServiceController {

    private final LookupServiceService lookupServiceService;

    public LookupServiceController (LookupServiceService lookupServiceService) {
        this.lookupServiceService = lookupServiceService;
    }

    @GetMapping("/ping")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("The service is up and running.");
    }

    @GetMapping("/credit-data/{ssn}")
    public ResponseEntity<CreditdataResponsePersonalDetails> getCreditdataPersonalDetails(@PathVariable String ssn) {
        var ret = lookupServiceService.getCreditdataPersonalDetails(ssn);
        return ResponseEntity.ok(ret);
    }


}
