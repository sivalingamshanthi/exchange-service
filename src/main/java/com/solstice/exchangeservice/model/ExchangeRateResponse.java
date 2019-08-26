package com.solstice.exchangeservice.model;

import javax.persistence.*;

@Entity
public class ExchangeRateResponse {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

    private String fromCurrency;
	private String toCurrency;
	private double conversion;

	public ExchangeRateResponse(){}

	public ExchangeRateResponse(String fromCurrency, String toCurrency, double conversion) {
		this.fromCurrency = fromCurrency;
		this.toCurrency = toCurrency;
		this.conversion = conversion;
	}

	public ExchangeRateResponse(Long id, String fromCurrency, String toCurrency, double conversion) {
		this.id = id;
		this.fromCurrency = fromCurrency;
		this.toCurrency = toCurrency;
		this.conversion = conversion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFromCurrency() {
		return fromCurrency;
	}

	public String getToCurrency() {
		return toCurrency;
	}

	public double getConversion() {
		return conversion;
	}
}
