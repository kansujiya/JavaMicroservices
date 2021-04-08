package com.xyz.microservices.currencyexchangeservice.currencyconversionservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;

@RestController
public class CurrencyConversionController {

    @Autowired
    CurrencyExchangeProxy currencyExchangeProxy;

    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversion(@PathVariable String from,
                                                          @PathVariable String to,
                                                          @PathVariable Integer quantity) {

        HashMap<String, String> uriVariables = new HashMap<String, String>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);
        ResponseEntity<CurrencyConversion> responseEntity = new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversion.class, uriVariables);
        CurrencyConversion currencyConversion = responseEntity.getBody();
        return new CurrencyConversion(currencyConversion.getId(), from, to, BigDecimal.valueOf(quantity), currencyConversion.getConversionMultiple(), BigDecimal.valueOf(quantity).multiply(currencyConversion.getConversionMultiple()), currencyConversion.getEnvironment()+ " " + "From RestTemplate");
    }

    @GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversionFeign(@PathVariable String from,
                                                          @PathVariable String to,
                                                          @PathVariable Integer quantity) {

        CurrencyConversion currencyConversion = currencyExchangeProxy.retriveExchangeValue(from, to);
        return new CurrencyConversion(currencyConversion.getId(), from, to, BigDecimal.valueOf(quantity), currencyConversion.getConversionMultiple(), BigDecimal.valueOf(quantity).multiply(currencyConversion.getConversionMultiple()), currencyConversion.getEnvironment() + " " + "From Feign");
    }
}
