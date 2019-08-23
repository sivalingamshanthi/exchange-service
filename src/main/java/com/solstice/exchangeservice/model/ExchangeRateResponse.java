package com.solstice.exchangeservice.model;

public class ExchangeRateResponse {

	private String from;
	private String to;
	private double conversion;

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
