package com.searchmetrics.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.searchmetrics.constant.Currency;
import com.searchmetrics.entity.ExchangeRateEntity;
import com.searchmetrics.model.ExchangeRate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;
import java.sql.Date;
import java.util.Map;

public class ExchangeRateUtil {
    private final static Logger logger = LoggerFactory.getLogger(ExchangeRateUtil.class);
    private static final String LAST = "last";
    public static Map<String, Map<String, String>> convertToMap(String json) {
        if (json == null || json.isEmpty()) {
            return null;
        }
        Gson gson = new Gson();
        Map<String, Map<String, String>> exchangeRateMap = null;
        Type exchangeRateMapType = new TypeToken<Map<String, Map<String, String>>>() {
        }.getType();
        try {
            exchangeRateMap = gson.fromJson(json, exchangeRateMapType);
        } catch (Exception ex) {
            logger.error("not able to parse json " + ex.getMessage());
        }
        return exchangeRateMap;
    }

    public static ExchangeRateEntity getExchangeRateEntity(Map<String, String> btcToUsdRateMap) {
        if (btcToUsdRateMap == null || btcToUsdRateMap.isEmpty()) {
            return null;
        }
        if (btcToUsdRateMap.get(LAST) == null && btcToUsdRateMap.get(LAST).isEmpty()) {
            return null;
        }
        return new ExchangeRateEntity(Currency.BTC.name(), Currency.USD.name(), Float.parseFloat(btcToUsdRateMap.get(LAST)), new Date(System.currentTimeMillis()));
    }

    public static ExchangeRate convert(ExchangeRateEntity entity) {
        if (entity == null) {
            return null;
        }
        return new ExchangeRate(entity.getFromCurrency(), entity.getToCurrency(), entity.getExchangeRate(), entity.getExchangeRateDate());
    }
}
