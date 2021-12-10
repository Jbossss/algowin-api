package org.market.hedge.deribit.service;

import org.market.hedge.deribit.DeribitExchange;
import org.market.hedge.deribit.dto.Kind;
import org.market.hedge.deribit.dto.marketdata.*;

import java.io.IOException;
import java.util.List;

/**
 * Implementation of the market data service for Deribit
 *
 * <ul>
 *   <li>Provides access to various market data values
 * </ul>
 */
public class DeribitMarketDataServiceRaw extends DeribitBaseService {

  public DeribitMarketDataServiceRaw(DeribitExchange exchange) {
    super(exchange);
  }

  public List<DeribitInstrument> getDeribitInstruments(String currency, Kind kind, Boolean expired)
      throws IOException {
    return deribit.getInstruments(currency, kind, expired).getResult();
  }

  public List<DeribitCurrency> getDeribitCurrencies() throws IOException {
    return deribit.getCurrencies().getResult();
  }

  public DeribitOrderBook getDeribitOrderBook(String instrumentName, Integer depth)
      throws IOException {
    return deribit.getOrderBook(instrumentName, depth).getResult();
  }


  public DeribitTrades getLastTradesByInstrument(
      String instrumentName,
      Integer startSeq,
      Integer endSeq,
      Integer count,
      Boolean includeOld,
      String sorting)
      throws IOException {
    return deribit
        .getLastTradesByInstrument(instrumentName, startSeq, endSeq, count, includeOld, sorting)
        .getResult();
  }

  public List<DeribitSummary> getSummaryByInstrument(String instrumentName) throws IOException {
    return deribit.getSummaryByInstrument(instrumentName).getResult();
  }

  public DeribitTicker getDeribitTicker(String instrumentName) throws IOException {
    return deribit.getTicker(instrumentName).getResult();
  }
}
