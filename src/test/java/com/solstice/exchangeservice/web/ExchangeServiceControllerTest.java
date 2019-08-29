package com.solstice.exchangeservice.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solstice.exchangeservice.service.ExchangeRateNotFoundException;
import com.solstice.exchangeservice.model.ExchangeRateResponse;
import com.solstice.exchangeservice.service.ExchangeServiceService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
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
		mockMvc.perform(MockMvcRequestBuilders.get("/exchange-rate?from=USD&to=INR"))
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
		mockMvc.perform(MockMvcRequestBuilders.get("/exchange-rate?from=INR&to=USD"))
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
				.willThrow(new ExchangeRateNotFoundException("Exchange Rate Not Found", "USA", "INR"));

		//act
		mockMvc.perform(MockMvcRequestBuilders.get("/exchange-rate?from=USA&to=INR"))
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("message").value("Exchange Rate Not Found"))
				.andExpect(jsonPath("fromCurrency").value("USA"))
				.andExpect(jsonPath("toCurrency").value("INR"));

	}

	@Test
	public void missingRequestParam() throws Exception{

		//act

		mockMvc.perform(MockMvcRequestBuilders.get("/exchange-rate?from=AUD"))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void addCurrency() throws Exception {
		given(exchangeServiceService.addExchangeRate(any(ExchangeRateResponse.class)))
				.willReturn("success");

		String jsonBody = new ObjectMapper().writeValueAsString(new ExchangeRateResponse("USD", "INR", 77.0));

		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/exchange-rate")
				.content(jsonBody).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andReturn();

		Assert.assertEquals("success", result.getResponse().getContentAsString());
	}
}
