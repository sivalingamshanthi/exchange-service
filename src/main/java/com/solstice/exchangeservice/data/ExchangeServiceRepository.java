package com.solstice.exchangeservice.data;

import com.solstice.exchangeservice.model.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeServiceRepository extends JpaRepository<ExchangeRate, Long> {

    ExchangeRate findByFromCurrencyAndToCurrency(String from, String to);
}
