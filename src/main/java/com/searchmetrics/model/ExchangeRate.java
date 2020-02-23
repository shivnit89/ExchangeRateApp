package com.searchmetrics.model;

import java.sql.Date;

public class ExchangeRate {
    private String fromCurrency;
    private String toCurrency;
    private Float exchangeRate;
    private Date exchangeRateDate;

    public ExchangeRate() {
    }

    public ExchangeRate(String fromCurrency, String toCurrency, Float exchangeRate, Date exchangeRateDate) {
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.exchangeRate = exchangeRate;
        this.exchangeRateDate = exchangeRateDate;
    }

    @Override
    public String toString() {
        return "ExchangeRate{" +
                "fromCurrency='" + fromCurrency + '\'' +
                ", toCurrency='" + toCurrency + '\'' +
                ", exchangeRate=" + exchangeRate +
                ", exchangeRateDate=" + exchangeRateDate +
                '}';
    }

    public String getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(String fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(String toCurrency) {
        this.toCurrency = toCurrency;
    }

    public Float getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(Float exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public Date getExchangeRateDate() {
        return exchangeRateDate;
    }

    public void setExchangeRateDate(Date exchangeRateDate) {
        this.exchangeRateDate = exchangeRateDate;
    }
}
