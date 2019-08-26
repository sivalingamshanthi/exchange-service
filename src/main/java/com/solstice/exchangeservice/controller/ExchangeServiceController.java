package com.solstice.exchangeservice.controller;

import com.solstice.exchangeservice.exception.ExchangeRateNotFoundException;
import com.solstice.exchangeservice.model.ExchangeRateResponse;
import com.solstice.exchangeservice.service.ExchangeServiceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExchangeServiceController {

	private final ExchangeServiceService exchangeServiceService;

	public ExchangeServiceController(ExchangeServiceService exchangeServiceService) {
		this.exchangeServiceService = exchangeServiceService;
	}

	@GetMapping("/exchange-rate")
	public ExchangeRateResponse getExchangeRate(@RequestParam String from, @RequestParam String to) {

		if(!(from.equals("USD") && to.equals("INR")))
			throw new ExchangeRateNotFoundException();

		return exchangeServiceService.getExchangeRate(from, to);
	}
}
