package com.solstice.exchangeservice.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"stackTrace", "localizedMessage", "suppressed", "cause"})
public class ExchangeRateNotFoundException extends RuntimeException {
    private String toCurrency;
    private String fromCurrency;

    public ExchangeRateNotFoundException(String message, String fromCurrency, String toCurrency) {
        super(message);
        this.toCurrency = toCurrency;
        this.fromCurrency = fromCurrency;

    }

    public String getToCurrency() {
        return toCurrency;
    }


    public String getFromCurrency() {
        return fromCurrency;
    }

}
