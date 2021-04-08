package com.xyz.microservices.currencyexchangeservice;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.security.PublicKey;

@RestController
public class CircuitBreakerController {

    private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);

    @Retry(name= "retry-api", fallbackMethod = "retryHardcodedResponse")
    @GetMapping("/retry-api")
    //@RateLimiter(name = "default")
    @Bulkhead(name = "default")
    public String retryApi() {
        logger.info("retry api call received");
        //ResponseEntity<String> responseEntity = new RestTemplate().getForEntity("http://sample-dummy-api", String.class);
        return "Retry api response with bulkhead";//responseEntity.getBody();
    }

    String retryHardcodedResponse(Exception ex) {
        return "fallback retry api response";
    }

    @CircuitBreaker(name= "circuit-breaker-api",fallbackMethod = "CircuitBreakerHardcodedResponse")
    @GetMapping("/circuit-breaker-api")
    public String circuitBreakerPpiApi() {
        logger.info("circuit breaker api call received");
        ResponseEntity<String> responseEntity = new RestTemplate().getForEntity("http://sample-dummy-api", String.class);
        return responseEntity.getBody();
    }

    String CircuitBreakerHardcodedResponse(Exception ex) {
        return "fallback circuit breaker api response";
    }
}
