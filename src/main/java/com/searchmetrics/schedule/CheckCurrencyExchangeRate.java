package com.searchmetrics.schedule;

import com.searchmetrics.client.HttpClient;
import com.searchmetrics.constant.Currency;
import com.searchmetrics.entity.ExchangeRateEntity;
import com.searchmetrics.repository.ExchangeRateRepository;
import com.searchmetrics.util.ExchangeRateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;

/**+
 * This class is cron job will constantly hit external service to get exchange rate and store them to H2 db.
 * exchange.rate.check.period is configured in application.properties file
 */
@Component
public class CheckCurrencyExchangeRate {
    private static final Logger logger = LoggerFactory.getLogger(CheckCurrencyExchangeRate.class);

    @Autowired
    private HttpClient httpClient;
    @Autowired
    private ExchangeRateRepository repository;
    @Value("${service.uri}")
    private String serviceURI;

    @Scheduled(cron = "${exchange.rate.check.period}")
    public void checkExchangeRate() {
        try {
            String exchangeRateJson = (String) httpClient.send(serviceURI, String.class);
            Map<String, Map<String, String>> exchangeRateMap = ExchangeRateUtil.convertToMap(exchangeRateJson);
            if (exchangeRateMap != null) {
                ExchangeRateEntity exchangeRateEntity = ExchangeRateUtil.getExchangeRateEntity(exchangeRateMap.get(Currency.USD.name()));
                if (exchangeRateEntity != null) {
                    repository.save(exchangeRateEntity);
                }
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
    }


}
