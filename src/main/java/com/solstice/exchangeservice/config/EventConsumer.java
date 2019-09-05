package com.solstice.exchangeservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solstice.exchangeservice.model.ExchangeRate;
import com.solstice.exchangeservice.service.ExchangeServiceService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.io.IOException;

public class EventConsumer {

    @Autowired
    ExchangeServiceService serviceService;

    @RabbitListener(queues = "orderServiceQueue")
    public void receive(Message message) {
        byte[] body = message.getBody();

        String s = new String(body);

        ObjectMapper mapper = new ObjectMapper();

        try {
            ExchangeRate rate = mapper.readValue(s, ExchangeRate.class);
            serviceService.addExchangeRate(rate);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Bean
    public SimpleMessageConverter getSimpleMessageConverter(){
        return new SimpleMessageConverter();
    }
}
