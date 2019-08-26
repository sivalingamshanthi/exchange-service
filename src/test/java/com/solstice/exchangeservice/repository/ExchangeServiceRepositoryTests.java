package com.solstice.exchangeservice.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.BDDMockito.given;

@DataJpaTest
@RunWith(SpringRunner.class)
public class ExchangeServiceRepositoryTests {

    @Autowired
//    private ExchangeServiceRepository exchangeServiceRepository;

    @Test
    public void findByFromAndTo_Success(){
//        exchangeServiceRepository.findByFromAndTo("USD", "INR")
    }
}
