package com.solstice.exchangeservice.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.solstice.exchangeservice.service.ExchangeRateNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(ExchangeRateNotFoundException.class)
    public ResponseEntity<ExchangeRateNotFoundException> handleExchangeRateNotFoundException(ExchangeRateNotFoundException e) {

        return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
    }

    @Override
    @JsonIgnoreProperties({"stackTrace", "localizedMessage", "suppressed", "cause"})
    public ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
                                                                          HttpHeaders headers,
                                                                          HttpStatus status,
                                                                          WebRequest request){
      String missingParameter = ex.getParameterName();
      String message="Missing Request Param:"+ missingParameter;
      return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }
}
