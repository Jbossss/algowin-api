package org.market.hedge.bitget.usdtswap.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.market.hedge.bitget.usdtswap.dto.marketdata.resp.BitgeUSwapDepthResult;
import org.market.hedge.core.ParsingCurrencyPair;
import org.market.hedge.service.marketdata.MHMarketDataService;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Implementation of the market data service for Bibox
 *
 * <ul>
 *   <li>Provides access to various market data values
 * </ul>
 *
 * @author odrotleff
 */
public class BitgetUSwapMarketDataService extends BitgetUSwapMarketDataServiceRaw implements MHMarketDataService {


  /**
   * Constructor
   *
   * @param exchange
   */
  public BitgetUSwapMarketDataService(Exchange exchange) {
    super(exchange);
  }

  /**
   * depth
   * @param parsingCurrencyPair
   * @param args  [0] Integer limit
   * @return
   * @throws IOException
   */
  @Override
  public OrderBook getOrderBook(ParsingCurrencyPair parsingCurrencyPair, Object... args) throws IOException {

      Integer limit=5;
      if (Objects.nonNull(args[0])){
          limit= (Integer) args[0];
      }


    BitgeUSwapDepthResult depth = getBitgetDepth(parsingCurrencyPair, limit);

        List<LimitOrder> bids =
                depth.getBids().stream()
                        .map(
                                e ->
                                        new LimitOrder(
                                                Order.OrderType.BID, new BigDecimal(e.get(1)), parsingCurrencyPair.getCurrencyPair(), null, null, new BigDecimal(e.get(0))))
                        .collect(Collectors.toList());
        List<LimitOrder> asks =
                depth.getAsks().stream()
                        .map(
                                e ->
                                        new LimitOrder(
                                                Order.OrderType.ASK, new BigDecimal(e.get(1)), parsingCurrencyPair.getCurrencyPair(), null, null, new BigDecimal(e.get(0))))
                        .collect(Collectors.toList());


    return new OrderBook(depth.getTimestamp(), asks, bids);
  }


}
