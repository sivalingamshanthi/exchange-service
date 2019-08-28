package com.solstice.exchangeservice.data;

import com.solstice.exchangeservice.model.ExchangeRateResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ExchangeServiceRepositoryTests {

    @Autowired
    public ExchangeServiceRepository exchangeServiceRepository;

    @Test
    public void findByFromAndTo_Success(){

        ExchangeRateResponse exchangeRateResponse =
                new ExchangeRateResponse("INR","USD",86.00);
        ExchangeRateResponse rateResponse = exchangeServiceRepository.save(exchangeRateResponse);

        ExchangeRateResponse exchangeRateResponse1 = exchangeServiceRepository
                .findByFromCurrencyAndToCurrency("INR", "USD");

        Assert.assertEquals(86.00, exchangeRateResponse1.getConversion(), 0);
        Assert.assertEquals("INR", exchangeRateResponse1.getFromCurrency());
        Assert.assertEquals("USD", exchangeRateResponse1.getToCurrency());
    }

}
