package com.solstice.exchangeservice.config;

import com.solstice.exchangeservice.model.ExchangeRate;
import com.solstice.exchangeservice.service.ExchangeServiceService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EventConsumer {

    @Autowired
    ExchangeServiceService serviceService;

    @RabbitListener(queues = "exchangeRateQueue")
    public void receive(ExchangeRate message) {

        serviceService.addExchangeRate(message);
    }
}
