package com.solstice.exchangeservice;

import com.solstice.exchangeservice.model.ExchangeRateResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ExchangeServiceIntegrationTest {

	@Autowired
	RestTemplate restTemplate;

	@Test
	public void success() {
		//arrange

		//act
		ResponseEntity<ExchangeRateResponse> exchangeRateResponse = restTemplate
				.getForEntity("http://localhost:8080/exchange-rate?from=USD&to=INR", ExchangeRateResponse.class);

		//assert
		Assert.assertEquals(HttpStatus.OK, exchangeRateResponse.getStatusCode());
		Assert.assertEquals("USD", exchangeRateResponse.getBody().getFrom());
		Assert.assertEquals("INR", exchangeRateResponse.getBody().getTo());
		Assert.assertEquals(72.0, exchangeRateResponse.getBody().getConversion());
	}

	@Test
	public void notFound() {

		//act
		ResponseEntity<ExchangeRateResponse> exchangeRateResponse = restTemplate
				.getForEntity("http://localhost:8080/exchange-rate?from=USD&to=INR", ExchangeRateResponse.class);

		Assert.assertEquals(HttpStatus.NOT_FOUND, exchangeRateResponse.getStatusCode());
	}
}
