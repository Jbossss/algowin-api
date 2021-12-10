package org.market.hedge.huobi.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.Order.IOrderFlags;
import org.knowm.xchange.dto.trade.*;
import org.knowm.xchange.service.trade.params.TradeHistoryParams;
import org.market.hedge.core.ParsingCurrencyPair;
import org.market.hedge.dto.trade.MHLimitOrder;
import org.market.hedge.dto.trade.MHMarketOrder;
import org.market.hedge.huobi.HuobiAdapters;
import org.market.hedge.huobi.dto.trade.HuobiOrder;
import org.market.hedge.service.trade.MHTradeService;
import org.knowm.xchange.service.trade.params.*;
import org.knowm.xchange.service.trade.params.orders.DefaultOpenOrdersParamCurrencyPair;
import org.knowm.xchange.service.trade.params.orders.OpenOrdersParams;

import java.io.IOException;
import java.util.Collection;

public class HuobiTradeService extends HuobiTradeServiceRaw implements MHTradeService {
  public static IOrderFlags FOK = new IOrderFlags() {};
  public static IOrderFlags IOC = new IOrderFlags() {};

  public HuobiTradeService(Exchange exchange) {
    super(exchange);
  }

  /** Huobi trade history only goes back 48h - a new API was promised in 2019-Q1 */
  @Override
  public UserTrades getTradeHistory(TradeHistoryParams tradeHistoryParams) throws IOException {
    if (!(tradeHistoryParams instanceof CurrencyPairParam)) throw new IllegalArgumentException();

    HuobiOrder[] openOrders = getHuobiTradeHistory((CurrencyPairParam) tradeHistoryParams);
    return HuobiAdapters.adaptTradeHistory(openOrders);
  }

  @Override
  public Collection<Order> getOrder(String... orderIds) throws IOException {
    return HuobiAdapters.adaptOrders(getHuobiOrder(orderIds));
  }

  @Override
  public OpenOrdersParams createOpenOrdersParams() {
    return new DefaultOpenOrdersParamCurrencyPair();
  }

  @Override
  public TradeHistoryParams createTradeHistoryParams() {
    return new DefaultTradeHistoryParamCurrencyPair();
  }

  @Override
  public boolean cancelOrder(String orderId) throws IOException {
    return cancelHuobiOrder(orderId).length() > 0;
  }

  @Override
  public boolean cancelOrder(CancelOrderParams cancelOrderParams) throws IOException {
    return cancelOrderParams instanceof CancelOrderByIdParams
        && cancelOrder(((CancelOrderByIdParams) cancelOrderParams).getOrderId());
  }

  @Override
  public String placeMarketOrder(MarketOrder marketOrder) throws IOException {
    return placeHuobiMarketOrder(marketOrder);
  }

  @Override
  public String placeMarketOrder(MHMarketOrder marketOrder) throws IOException {
    return placeHuobiMarketOrder(marketOrder);
  }

  @Override
  public OpenOrders getOpenOrders() throws IOException {
    return getOpenOrders(createOpenOrdersParams());
  }

  @Override
  public OpenOrders getOpenOrders(OpenOrdersParams openOrdersParams) throws IOException {
    if (!(openOrdersParams instanceof CurrencyPairParam)) throw new IllegalArgumentException();

    HuobiOrder[] openOrders = getHuobiOpenOrders((CurrencyPairParam) openOrdersParams);
    return HuobiAdapters.adaptOpenOrders(openOrders);
  }

  @Override
  public String placeLimitOrder(MHLimitOrder LimitOrder) throws IOException {
    return placeHuobiLimitOrder(LimitOrder);
  }

  @Override
  public String placeStopOrder(StopOrder stopOrder) throws IOException {
    return null;
  }

  @Override
  public void cancelAllByInstrument(ParsingCurrencyPair parsingCurrencyPair) throws IOException {

  }
}
