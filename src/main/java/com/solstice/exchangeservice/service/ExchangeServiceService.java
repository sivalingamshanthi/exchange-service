package com.solstice.exchangeservice.service;

import com.solstice.exchangeservice.exception.ExchangeRateNotFoundException;
import com.solstice.exchangeservice.model.ExchangeRateResponse;
import com.solstice.exchangeservice.repository.ExchangeServiceRepository;
import org.springframework.stereotype.Service;

@Service
public class ExchangeServiceService {

	final ExchangeServiceRepository exchangeServiceRepository;

	public ExchangeServiceService(ExchangeServiceRepository exchangeServiceRepository) {
		this.exchangeServiceRepository = exchangeServiceRepository;
	}

	public ExchangeRateResponse getExchangeRate(String from, String to) {
		String message = "Exchange Rate Not Found";

		//Call the repo interface method
		ExchangeRateResponse exchangeRateResponse = exchangeServiceRepository
				.findByFromCurrencyAndToCurrency(from, to);

		if(exchangeRateResponse==null){
			throw new ExchangeRateNotFoundException(message, from, to);
		}
		return exchangeRateResponse;
	}
}
