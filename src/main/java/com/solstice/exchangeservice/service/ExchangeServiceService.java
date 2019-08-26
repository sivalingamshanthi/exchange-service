package com.solstice.exchangeservice.service;

import com.solstice.exchangeservice.model.ExchangeRateResponse;
import com.solstice.exchangeservice.repository.ExchangeServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExchangeServiceService {

	@Autowired
	ExchangeServiceRepository serviceService;

	public ExchangeRateResponse getExchangeRate(String from, String to) {

		//test

		if(from.equals("USD") && to.equals("INR"))
			return new ExchangeRateResponse(from, to, 72.00);
		else
			return new ExchangeRateResponse(from, to, 86.00);
	}
}
