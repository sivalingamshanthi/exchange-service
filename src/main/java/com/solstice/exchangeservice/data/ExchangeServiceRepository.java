package com.solstice.exchangeservice.data;

import com.solstice.exchangeservice.model.ExchangeRateResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeServiceRepository extends JpaRepository<ExchangeRateResponse, Long> {

    ExchangeRateResponse findByFromCurrencyAndToCurrency(String from, String to);
}
