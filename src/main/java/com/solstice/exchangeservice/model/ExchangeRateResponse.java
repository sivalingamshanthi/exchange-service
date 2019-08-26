package com.solstice.exchangeservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ExchangeRateResponse {



    private String from;
	private String to;
	private double conversion;

	public ExchangeRateResponse(){}

	public ExchangeRateResponse(String from, String to, double conversion) {
		this.from = from;
		this.to = to;
		this.conversion = conversion;
	}

	public String getFrom() {
		return from;
	}

	public String getTo() {
		return to;
	}

	public double getConversion() {
		return conversion;
	}
}
