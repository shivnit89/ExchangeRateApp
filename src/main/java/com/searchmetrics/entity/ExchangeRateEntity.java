package com.searchmetrics.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity()
@Table(name = "currency_exchange_rate")
public class ExchangeRateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "from_currency")
    private String fromCurrency;
    @Column(name = "to_currency")
    private String toCurrency;
    @Column(name = "exchange_rate")
    private Float exchangeRate;
    @Column(name = "exchange_rate_date")
    private Date exchangeRateDate;

    public ExchangeRateEntity() {
    }

    public ExchangeRateEntity(String fromCurrency, String toCurrency, Float exchangeRate, Date exchangeRateDate) {
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.exchangeRate = exchangeRate;
        this.exchangeRateDate = exchangeRateDate;
    }

    public Long getId() {
        return id;
    }

    public String getFromCurrency() {
        return fromCurrency;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public Float getExchangeRate() {
        return exchangeRate;
    }

    public Date getExchangeRateDate() {
        return exchangeRateDate;
    }

    @Override
    public String toString() {
        return "ExchangeRateEntity{" +
                "id=" + id +
                ", fromCurrency='" + fromCurrency + '\'' +
                ", toCurrency='" + toCurrency + '\'' +
                ", exchangeRate=" + exchangeRate +
                ", exchangeRateDate=" + exchangeRateDate +
                '}';
    }
}
