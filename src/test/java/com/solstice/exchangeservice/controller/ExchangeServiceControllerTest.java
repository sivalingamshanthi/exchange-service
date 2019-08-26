package com.solstice.exchangeservice.controller;

import com.solstice.exchangeservice.exception.ExchangeRateNotFoundException;
import com.solstice.exchangeservice.model.ExchangeRateResponse;
import com.solstice.exchangeservice.service.ExchangeServiceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ExchangeServiceController.class)
@RunWith(SpringRunner.class)
public class ExchangeServiceControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	ExchangeServiceService exchangeServiceService;

	@Test
	public void controllerTest_USDToINR() throws Exception {

		//arrange
		given(exchangeServiceService.getExchangeRate(anyString(), anyString()))
				.willReturn(new ExchangeRateResponse("USD", "INR", 72.0));

		//act
		mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/exchange-rate?from=USD&to=INR"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("fromCurrency").value("USD"))
				.andExpect(jsonPath("toCurrency").value("INR"))
				.andExpect(jsonPath("conversion").value(72.0));
		//assert
	}

	@Test
	public void controllerTest_INRToUSD() throws Exception {

		//arrange
		given(exchangeServiceService.getExchangeRate(anyString(), anyString()))
				.willReturn(new ExchangeRateResponse("INR", "USD", 72.0));

		//act
		mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/exchange-rate?from=INR&to=USD"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("fromCurrency").value("INR"))
				.andExpect(jsonPath("toCurrency").value("USD"))
				.andExpect(jsonPath("conversion").value(72.0));
		//assert
	}

	@Test
	public void controllerTest_fromNotFound() throws Exception {

		//arrange
		given(exchangeServiceService.getExchangeRate(anyString(), anyString()))
				.willThrow(new ExchangeRateNotFoundException());

		//act
		mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/exchange-rate?from=INR&to=USD"))
				.andExpect(status().isNotFound());
	}
}
