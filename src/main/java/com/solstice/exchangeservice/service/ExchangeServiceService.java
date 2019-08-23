package com.solstice.exchangeservice.service;

import com.solstice.exchangeservice.exception.ExchangeRateNotFoundException;
import com.solstice.exchangeservice.model.ExchangeRateResponse;
import org.springframework.stereotype.Service;

@Service
public class ExchangeServiceService {

	public ExchangeRateResponse getExchangeRate(String from, String to) {
		throw new ExchangeRateNotFoundException();
	}
}
