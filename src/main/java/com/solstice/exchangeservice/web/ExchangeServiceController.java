package com.solstice.exchangeservice.web;

import com.solstice.exchangeservice.model.ExchangeRateResponse;
import com.solstice.exchangeservice.service.ExchangeServiceService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

	@PostMapping("/exchange-rate")
	@ResponseStatus(HttpStatus.CREATED)
	public String addExchangeRate(@RequestBody ExchangeRateResponse rateResponse){
		return exchangeServiceService.addExchangeRate(rateResponse);
	}
}
