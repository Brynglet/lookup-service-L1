package com.example.demo.controller;

import com.example.demo.exception.ApiError;
import com.example.demo.service.LookupServiceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

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
    public ResponseEntity<Map<String, Object>> getLookupServiceResponse(@PathVariable String ssn) {
        try {
            var ret = lookupServiceService.getLookupServiceResponse(ssn);
            return ResponseEntity.ok(ret);
        } catch (ApiError e) {
            if (HttpStatus.NOT_FOUND.equals(e.getHttpStatusCode())) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
