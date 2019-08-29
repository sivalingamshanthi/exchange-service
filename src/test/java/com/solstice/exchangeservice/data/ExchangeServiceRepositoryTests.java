package com.solstice.exchangeservice.data;

import com.solstice.exchangeservice.model.ExchangeRate;
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

        ExchangeRate exchangeRate =
                new ExchangeRate("INR","USD",86.00);
        ExchangeRate rateResponse = exchangeServiceRepository.save(exchangeRate);

        ExchangeRate exchangeRate1 = exchangeServiceRepository
                .findByFromCurrencyAndToCurrency("INR", "USD");

        Assert.assertEquals(86.00, exchangeRate1.getConversion(), 0);
        Assert.assertEquals("INR", exchangeRate1.getFromCurrency());
        Assert.assertEquals("USD", exchangeRate1.getToCurrency());
    }


}
