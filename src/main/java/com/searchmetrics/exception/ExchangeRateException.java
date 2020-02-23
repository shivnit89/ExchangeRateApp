package com.searchmetrics.exception;

public class ExchangeRateException extends RuntimeException {
    public ExchangeRateException(String msg) {
        super(msg);
    }

    public ExchangeRateException() {
        super();
    }
}
