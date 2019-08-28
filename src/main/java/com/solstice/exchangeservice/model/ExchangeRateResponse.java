package com.solstice.exchangeservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeRateResponse {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore
	private Long id;

    private String fromCurrency;
	private String toCurrency;
	private double conversion;

	public ExchangeRateResponse(String fromCurrency, String toCurrency, double conversion) {
		this.fromCurrency = fromCurrency;
		this.toCurrency = toCurrency;
		this.conversion = conversion;
	}
}
