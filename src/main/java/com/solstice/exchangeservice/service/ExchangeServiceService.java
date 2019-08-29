package com.solstice.exchangeservice.service;

import com.solstice.exchangeservice.data.ExchangeServiceRepository;
import com.solstice.exchangeservice.model.ExchangeRateResponse;
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

	public String addExchangeRate(ExchangeRateResponse response){

		String s = "";

		ExchangeRateResponse r = exchangeServiceRepository
				.findByFromCurrencyAndToCurrency(response.getFromCurrency(), response.getToCurrency());

		if(r == null){
			//add the record
			exchangeServiceRepository.save(response);
			s = "success";
		} else {
			//update
			if(r.getConversion() != response.getConversion()){
				r.setConversion(response.getConversion());
				exchangeServiceRepository.save(r);
				s =  "success";
			} else {
				throw new ResourceAlreadyExistsException("Value already exists", r.getFromCurrency(), r.getToCurrency(), r.getConversion());
			}
		}

		return s;
	}
}
