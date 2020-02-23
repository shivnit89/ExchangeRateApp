package com.searchmetrics.repository;

import com.searchmetrics.entity.ExchangeRateEntity;
import com.searchmetrics.model.HistoricalData;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

/**+
 * This class will be use to perform CURD operation in underlying data source i.e H2 in-memory db
 */
@Repository
public interface ExchangeRateRepository extends CrudRepository<ExchangeRateEntity, Long> {

    String GET_HISTORICAL_DATA_HQL_QUERY = "select t.exchangeRateDate as exchangeRateDate, max(t.exchangeRate) as maxExchangeRate,min(t.exchangeRate) as minExchangeRate,t.fromCurrency as fromCurrency,t.toCurrency as toCurrency from ExchangeRateEntity t where t.exchangeRateDate between :startDate and :endDate group by t.exchangeRateDate";

    @Query(GET_HISTORICAL_DATA_HQL_QUERY)
     List<HistoricalData> findHistoricalData(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
