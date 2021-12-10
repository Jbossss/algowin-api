package org.market.hedge.deribit.service;

import org.knowm.xchange.currency.CurrencyPair;
import org.market.hedge.deribit.DeribitAdapters;
import org.market.hedge.deribit.DeribitExchange;
import org.market.hedge.core.ParsingCurrencyPair;
import org.market.hedge.deribit.dto.DeribitException;
import org.market.hedge.deribit.dto.Kind;
import org.market.hedge.deribit.dto.marketdata.DeribitInstrument;
import org.market.hedge.deribit.dto.marketdata.DeribitOrderBook;
import org.market.hedge.deribit.dto.marketdata.DeribitTicker;
import org.market.hedge.deribit.dto.marketdata.DeribitTrades;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.dto.marketdata.Trades;
import org.knowm.xchange.exceptions.ExchangeException;
import org.market.hedge.service.marketdata.MHMarketDataService;

import java.io.IOException;
import java.util.List;

/**
 * Implementation of the market data service for Bitmex
 *
 * <ul>
 *   <li>Provides access to various market data values
 * </ul>
 */
public class DeribitMarketDataService extends DeribitMarketDataServiceRaw implements MHMarketDataService {

  /**
   * Constructor
   *
   * @param exchange
   */
  public DeribitMarketDataService(DeribitExchange exchange) {

    super(exchange);
  }

  @Override
  public Ticker getTicker(CurrencyPair currencyPair, Object... args) throws IOException {
    String deribitInstrumentName = DeribitAdapters.adaptInstrumentName(currencyPair, args);
    DeribitTicker deribitTicker;

    try {
      deribitTicker = super.getDeribitTicker(deribitInstrumentName);
    } catch (DeribitException ex) {
      throw DeribitAdapters.adapt(ex);
    }
    return DeribitAdapters.adaptTicker(deribitTicker);
  }

  @Override
  public OrderBook getOrderBook(ParsingCurrencyPair currencyPair, Object... args) throws IOException {
    String deribitInstrumentName = DeribitAdapters.adaptInstrumentName(currencyPair.getCurrencyPair(),args);
    DeribitOrderBook deribitOrderBook;
    try {
      deribitOrderBook = super.getDeribitOrderBook(deribitInstrumentName, null);
    } catch (DeribitException ex) {
      throw new ExchangeException(ex);
    }

    return DeribitAdapters.adaptOrderBook(deribitOrderBook);
  }

  @Override
  public OrderBook getOrderBook(CurrencyPair currencyPair, Object... args) throws IOException {
    String deribitInstrumentName = DeribitAdapters.adaptInstrumentName(currencyPair, args);
    DeribitOrderBook deribitOrderBook;
    try {
      deribitOrderBook = super.getDeribitOrderBook(deribitInstrumentName, null);
    } catch (DeribitException ex) {
      throw new ExchangeException(ex);
    }

    return DeribitAdapters.adaptOrderBook(deribitOrderBook);
  }

  @Override
  public Trades getTrades(CurrencyPair currencyPair, Object... args) throws IOException {
    String deribitInstrumentName = DeribitAdapters.adaptInstrumentName(currencyPair, args);
    DeribitTrades deribitTrades;

    try {
      deribitTrades =
          super.getLastTradesByInstrument(deribitInstrumentName, null, null, null, null, null);
    } catch (DeribitException ex) {
      throw new ExchangeException(ex);
    }

    return DeribitAdapters.adaptTrades(deribitTrades);
  }

}
