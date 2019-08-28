package com.solstice.exchangeservice.service;

import com.solstice.exchangeservice.model.ExchangeRateResponse;
import com.solstice.exchangeservice.data.ExchangeServiceRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;

@WebMvcTest(ExchangeServiceService.class)
@RunWith(SpringRunner.class)
public class ExchangeServiceServiceTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ExchangeServiceService exchangeServiceService;

    @MockBean
    ExchangeServiceRepository exchangeServiceRepository;



    @Test
    public void serviceTest_USDTOINR_Success() {

        given(exchangeServiceRepository.findByFromCurrencyAndToCurrency(Mockito.anyString(), Mockito.anyString()))
                .willReturn(new ExchangeRateResponse("USD", "INR", 72.00));

        ExchangeRateResponse exchangeRateResponse =
                exchangeServiceService.getExchangeRate("USD", "INR");

        Assert.assertEquals(72.00, exchangeRateResponse.getConversion(), 0);
        Assert.assertEquals("USD", exchangeRateResponse.getFromCurrency());
        Assert.assertEquals("INR", exchangeRateResponse.getToCurrency());
    }

    @Test
    public void serviceTest_INRTOUSD_getExchangeRate_Success() {

        given(exchangeServiceRepository.findByFromCurrencyAndToCurrency(Mockito.anyString(), Mockito.anyString()))
                .willReturn(new ExchangeRateResponse("INR", "USD", 86.00));
        ExchangeRateResponse exchangeRateResponse =
                exchangeServiceService.getExchangeRate("INR", "USD");

        Assert.assertEquals(86.00, exchangeRateResponse.getConversion(), 0);
        Assert.assertEquals("INR", exchangeRateResponse.getFromCurrency());
        Assert.assertEquals("USD", exchangeRateResponse.getToCurrency());
    }
}
