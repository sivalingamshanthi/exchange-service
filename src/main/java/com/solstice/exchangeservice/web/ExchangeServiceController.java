package com.solstice.exchangeservice.web;

import com.solstice.exchangeservice.model.ExchangeRate;
import com.solstice.exchangeservice.model.GenericResponse;
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
	public ExchangeRate getExchangeRate(@RequestParam String from, @RequestParam String to) {

		return exchangeServiceService.getExchangeRate(from, to);
	}

	@PostMapping("/exchange-rate")
	@ResponseStatus(HttpStatus.CREATED)
	public GenericResponse addExchangeRate(@RequestBody ExchangeRate rateResponse){
		exchangeServiceService.addExchangeRate(rateResponse);
		return new GenericResponse("success");
	}
}
