package com.solstice.exchangeservice;

import com.solstice.exchangeservice.model.ExchangeRateResponse;
import com.solstice.exchangeservice.repository.ExchangeServiceRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ExchangeServiceIntegrationTest {

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	public ExchangeServiceRepository exchangeServiceRepository;

	@Test
	public void success() {
		//arrange
//		ExchangeRateResponse rateResponse = exchangeServiceRepository.save(new ExchangeRateResponse("USD","INR",72.00));
		//act
		ResponseEntity<ExchangeRateResponse> exchangeRateResponse = restTemplate
				.getForEntity("http://localhost:8080/exchange-rate?from=USD&to=INR", ExchangeRateResponse.class);

		//assert
		Assert.assertEquals(HttpStatus.OK, exchangeRateResponse.getStatusCode());
		Assert.assertEquals("USD", exchangeRateResponse.getBody().getFromCurrency());
		Assert.assertEquals("INR", exchangeRateResponse.getBody().getToCurrency());
		Assert.assertEquals(86.00, exchangeRateResponse.getBody().getConversion(), 0);
	}

	@Test(expected = HttpClientErrorException.NotFound.class)
	public void notFound() {

		//act
			ResponseEntity<Exception> exchangeRateResponse = restTemplate
					.getForEntity("http://localhost:8080/exchange-rate?from=USA&to=INR", Exception.class);

	}


}
