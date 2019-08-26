package com.solstice.exchangeservice.repository;

import com.solstice.exchangeservice.model.ExchangeRateResponse;
import org.springframework.data.repository.CrudRepository;

public interface ExchangeServiceRepository extends CrudRepository<ExchangeRateResponse, Integer> {


}
