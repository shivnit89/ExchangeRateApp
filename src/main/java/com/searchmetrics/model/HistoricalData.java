package com.searchmetrics.model;

import java.sql.Date;

/**+
 * This interface will use to hold historical data returned for curd repository
 */
public interface HistoricalData {
    Date getExchangeRateDate();

    String getMaxExchangeRate();

    String getMinExchangeRate();

    String getFromCurrency();

    String getToCurrency();
}
