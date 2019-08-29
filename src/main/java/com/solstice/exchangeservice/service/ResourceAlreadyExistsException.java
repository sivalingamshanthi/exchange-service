package com.solstice.exchangeservice.service;

public class ResourceAlreadyExistsException extends RuntimeException {

    private String toCurrency;
    private String fromCurrency;
    private Double conversion;

    public ResourceAlreadyExistsException(String message, String fromCurrency, String toCurrency, Double conversion) {
        super(message);
        this.toCurrency = toCurrency;
        this.fromCurrency = fromCurrency;
        this.conversion = conversion;
    }
}
