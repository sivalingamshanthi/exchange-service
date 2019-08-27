package com.solstice.exchangeservice.controller;

import com.solstice.exchangeservice.model.ExchangeRateResponse;
import com.solstice.exchangeservice.service.ExchangeServiceService;
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

		return exchangeServiceService.getExchangeRate(from, to);
	}
}

//from or to is missing