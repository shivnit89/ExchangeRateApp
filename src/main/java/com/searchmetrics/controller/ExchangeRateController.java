package com.searchmetrics.controller;

import com.searchmetrics.constant.Currency;
import com.searchmetrics.exception.BadRequestException;
import com.searchmetrics.exception.ExchangeRateException;
import com.searchmetrics.exception.NoDataFoundException;
import com.searchmetrics.model.HistoricalData;
import com.searchmetrics.repository.ExchangeRateRepository;
import com.searchmetrics.client.HttpClient;
import com.searchmetrics.model.ExchangeRate;
import com.searchmetrics.util.ExchangeRateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/exchange")
public class ExchangeRateController {

    @Autowired
    private HttpClient httpClient;
    @Value("${service.uri}")
    private String uri;
    @Autowired
    private ExchangeRateRepository repository;

    /**
     * This method will return latest rate of BTC to USD by calling external service
     *
     * @return exchangeRate
     */
    @RequestMapping(value = "/getLatestRate", method = RequestMethod.GET)
    public ExchangeRate getLatestRate() {
        String exchangeRateJson = (String) httpClient.send(uri, String.class);
        Map<String, Map<String, String>> exchangeRateMap = ExchangeRateUtil.convertToMap(exchangeRateJson);
        ExchangeRate exchangeRate = null;
        if (exchangeRateMap != null) {
            exchangeRate = ExchangeRateUtil.convert(ExchangeRateUtil.getExchangeRateEntity(exchangeRateMap.get(Currency.USD.name())));
        }
        if (exchangeRate == null) {
            throw new ExchangeRateException("Service is currently down please try after some time");
        }
        return exchangeRate;
    }

    /**
     * This method will return historical exchange rate data of BTC to USD between given dates.
     * Currently using in-memory H2 db so only current date data will be present in the system.
     * @param startDate
     * @param endDate
     * @return
     */
    @RequestMapping(value = "/getHistoricalRate", method = RequestMethod.GET)
    public List<HistoricalData> getHistoricalRate(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) {
        if (startDate == null || endDate == null) {
            throw new BadRequestException("invalid dates");
        }
        Date sDate = null;
        Date eDate = null;
        try {
            sDate = Date.valueOf(startDate);
            eDate = Date.valueOf(endDate);

        } catch (IllegalArgumentException ex) {
            throw new BadRequestException("Invalid startDate:" + startDate + " or endDate:" + endDate + " expected format YYYY-mm-dd");
        }
        List<HistoricalData> historicalExchangeRates = repository.findHistoricalData(sDate, eDate);
        if (historicalExchangeRates == null || historicalExchangeRates.isEmpty()) {
            throw new NoDataFoundException("No data available between given dates startDate:" + startDate + " endDate:" + endDate);
        }
        return historicalExchangeRates;
    }
}

