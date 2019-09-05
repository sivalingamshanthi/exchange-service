package com.solstice.exchangeservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;

public class MessagingConfig {

    @Bean
    public DirectExchange getExchange(){
        return new DirectExchange("eventExchange");
    }

    @Bean
    public Queue queue() {
        return new Queue("orderServiceQueue");
    }

    @Bean
    public Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with("POSTMESSAGE");
    }

    @Bean
    public EventConsumer eventReceiver() {
        return new EventConsumer();
    }
}