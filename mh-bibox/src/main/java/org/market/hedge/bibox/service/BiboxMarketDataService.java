package org.market.hedge.bibox.service;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.market.hedge.bibox.dto.BiboxAdapters;
import org.market.hedge.bibox.dto.marketdata.BiboxMarket;
import org.market.hedge.bibox.dto.trade.BiboxDeals;
import org.market.hedge.bibox.dto.trade.BiboxOrderBook;
import org.market.hedge.core.ParsingCurrencyPair;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.dto.marketdata.Trades;
import org.knowm.xchange.dto.meta.ExchangeMetaData;
import org.market.hedge.service.marketdata.MHMarketDataService;

/**
 * Implementation of the market data service for Bibox
 *
 * <ul>
 *   <li>Provides access to various market data values
 * </ul>
 *
 * @author odrotleff
 */
public class BiboxMarketDataService extends BiboxMarketDataServiceRaw implements MHMarketDataService {

  /**
   * Constructor
   *
   * @param exchange
   */
  public BiboxMarketDataService(Exchange exchange) {

    super(exchange);
  }

  @Override
  public Ticker getTicker(CurrencyPair currencyPair, Object... args) throws IOException {
    return BiboxAdapters.adaptTicker(getBiboxTicker(currencyPair), currencyPair);
  }

  @Override
  public OrderBook getOrderBook(ParsingCurrencyPair parsing, Object... args) throws IOException {
    Integer depth = 16; // default on website

    if (args != null && args.length > 0) {
      if (args[0] instanceof Integer && (Integer) args[0] > 0) {
        depth = (Integer) args[0];
      }
    }
    BiboxOrderBook biboxOrderBook = getBiboxOrderBook(parsing.getCurrencyPair(), depth);
    return BiboxAdapters.adaptOrderBook(biboxOrderBook, parsing.getCurrencyPair());
  }

  public List<OrderBook> getAllOrderBooks(Integer depth) {
    return getOrderBooks(depth, exchange.getExchangeSymbols());
  }

  public List<OrderBook> getOrderBooks(Integer depth, Collection<CurrencyPair> currencyPairs) {

    if (depth == null) {
      depth = 200;
    }
    List<BiboxOrderBook> biboxOrderBooks = getBiboxOrderBooks(depth, currencyPairs);
    return BiboxAdapters.adaptAllOrderBooks(biboxOrderBooks);
  }

  @Override
  public Trades getTrades(CurrencyPair currencyPair, Object... args) throws IOException {
    Integer depth = 200;
    if (args != null && args.length > 0 && args[0] instanceof Integer && (Integer) args[0] > 0) {
      depth = (Integer) args[0];
    }
    List<BiboxDeals> biboxDeals = getBiboxDeals(currencyPair, depth);
    return BiboxAdapters.adaptDeals(biboxDeals, currencyPair);
  }

  public ExchangeMetaData getMetadata() throws IOException {
    List<BiboxMarket> markets = getAllBiboxMarkets();
    return BiboxAdapters.adaptMetadata(markets);
  }
}
