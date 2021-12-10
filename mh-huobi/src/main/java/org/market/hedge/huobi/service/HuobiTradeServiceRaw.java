package org.market.hedge.huobi.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order.OrderType;
import org.knowm.xchange.dto.trade.MarketOrder;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.service.trade.params.CurrencyPairParam;
import org.market.hedge.dto.trade.MHLimitOrder;
import org.market.hedge.dto.trade.MHMarketOrder;
import org.market.hedge.huobi.HuobiUtils;
import org.market.hedge.huobi.dto.trade.HuobiCreateOrderRequest;
import org.market.hedge.huobi.dto.trade.HuobiMatchResult;
import org.market.hedge.huobi.dto.trade.HuobiOrder;
import org.market.hedge.huobi.dto.trade.results.*;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HuobiTradeServiceRaw extends HuobiBaseService {
  HuobiTradeServiceRaw(Exchange exchange) {
    super(exchange);
  }
  // https://huobiapi.github.io/docs/spot/v1/en/#search-past-orders
  public HuobiOrder[] getHuobiTradeHistory(CurrencyPairParam params) throws IOException {
    String tradeStates = "partial-filled,partial-canceled,filled";
    HuobiOrdersResult result =
        huobi.getOrders(
            params != null ? HuobiUtils.createHuobiCurrencyPair(params.getCurrencyPair()) : null,
            tradeStates,
            null, // System.currentTimeMillis() - 48 * 60 * 60_000L,
            null,
            null,
            exchange.getExchangeSpecification().getApiKey(),
            HuobiDigest.HMAC_SHA_256,
            2,
            HuobiUtils.createUTCDate(exchange.getNonceFactory()),
            signatureCreator);
    return checkResult(result);
  }

  public HuobiOrder[] getHuobiOrderHistory(
      CurrencyPairParam params, Date startTime, Date endTime, String direct, Integer size)
      throws IOException {
    HuobiOrdersResult result =
        huobi.getOrdersHistory(
            params != null ? HuobiUtils.createHuobiCurrencyPair(params.getCurrencyPair()) : null,
            startTime != null ? startTime.getTime() : null,
            endTime != null ? endTime.getTime() : null,
            direct,
            size,
            exchange.getExchangeSpecification().getApiKey(),
            HuobiDigest.HMAC_SHA_256,
            2,
            HuobiUtils.createUTCDate(exchange.getNonceFactory()),
            signatureCreator);
    return checkResult(result);
  }

  public HuobiOrder[] getHuobiOpenOrders(CurrencyPairParam params) throws IOException {
    String states = "pre-submitted,submitted,partial-filled";
    org.market.hedge.huobi.dto.trade.results.HuobiOrdersResult result =
        huobi.getOpenOrders(
            params != null ? HuobiUtils.createHuobiCurrencyPair(params.getCurrencyPair()) : null,
            states,
            exchange.getExchangeSpecification().getApiKey(),
            HuobiDigest.HMAC_SHA_256,
            2,
            HuobiUtils.createUTCDate(exchange.getNonceFactory()),
            signatureCreator);
    return checkResult(result);
  }

  public HuobiMatchResult[] getHuobiMatchResults(
      CurrencyPairParam params,
      String types,
      Date startDate,
      Date endDate,
      String from,
      String direct,
      Integer size)
      throws IOException {
    HuobiMatchesResult result =
        huobi.getMatchResults(
            params != null ? HuobiUtils.createHuobiCurrencyPair(params.getCurrencyPair()) : null,
            types,
            HuobiUtils.createUTCDate(startDate),
            HuobiUtils.createUTCDate(endDate),
            from,
            direct,
            size,
            exchange.getExchangeSpecification().getApiKey(),
            HuobiDigest.HMAC_SHA_256,
            2,
            HuobiUtils.createUTCDate(exchange.getNonceFactory()),
            signatureCreator);
    return checkResult(result);
  }

  public String cancelHuobiOrder(String orderId) throws IOException {
    HuobiCancelOrderResult result =
        huobi.cancelOrder(
            orderId,
            exchange.getExchangeSpecification().getApiKey(),
            HuobiDigest.HMAC_SHA_256,
            2,
            HuobiUtils.createUTCDate(exchange.getNonceFactory()),
            signatureCreator);
    return checkResult(result);
  }

  public String placeHuobiLimitOrder(MHLimitOrder LimitOrder) throws IOException {
    String type;
    if (LimitOrder.getType() == OrderType.BID) {
      type = "buy-limit";
    } else if (LimitOrder.getType() == OrderType.ASK) {
      type = "sell-limit";
    } else {
      throw new ExchangeException("Unsupported order type.");
    }
    if (LimitOrder.hasFlag(HuobiTradeService.FOK)) type = type + "-fok";
    if (LimitOrder.hasFlag(HuobiTradeService.IOC)) type = type + "-ioc";

    HuobiOrderResult result =
        huobi.placeLimitOrder(
            new HuobiCreateOrderRequest(
                getAccountId(),
                LimitOrder.getOriginalAmount().toString(),
                LimitOrder.getLimitPrice().toString(),
                HuobiUtils.createHuobiCurrencyPair(LimitOrder.getParsingCurrencyPair().getCurrencyPair()),
                type,
                LimitOrder.getUserReference(),
                null,
                null),
            exchange.getExchangeSpecification().getApiKey(),
            HuobiDigest.HMAC_SHA_256,
            2,
            HuobiUtils.createUTCDate(exchange.getNonceFactory()),
            signatureCreator);

    return checkResult(result);
  }

  public String placeHuobiMarketOrder(MHMarketOrder order) throws IOException {
    String type;
    if (order.getType() == OrderType.BID) {
      type = "buy-market";
    } else if (order.getType() == OrderType.ASK) {
      type = "sell-market";
    } else {
      throw new ExchangeException("Unsupported order type.");
    }

    HuobiOrderResult result =
            huobi.placeMarketOrder(
                    new HuobiCreateOrderRequest(
                            getAccountId(),
                            order.getOriginalAmount().toString(),
                            null,
                            HuobiUtils.createHuobiCurrencyPair(order.getParsingCurrencyPair().getCurrencyPair()),
                            type,
                            order.getUserReference(),
                            null,
                            null),
                    exchange.getExchangeSpecification().getApiKey(),
                    HuobiDigest.HMAC_SHA_256,
                    2,
                    HuobiUtils.createUTCDate(exchange.getNonceFactory()),
                    signatureCreator);
    return checkResult(result);
  }

  public String placeHuobiMarketOrder(MarketOrder order) throws IOException {
    String type;
    if (order.getType() == OrderType.BID) {
      type = "buy-market";
    } else if (order.getType() == OrderType.ASK) {
      type = "sell-market";
    } else {
      throw new ExchangeException("Unsupported order type.");
    }

    HuobiOrderResult result =
        huobi.placeMarketOrder(
            new HuobiCreateOrderRequest(
                getAccountId(),
                order.getOriginalAmount().toString(),
                null,
                HuobiUtils.createHuobiCurrencyPair((CurrencyPair) order.getInstrument()),
                type,
                order.getUserReference(),
                null,
                null),
            exchange.getExchangeSpecification().getApiKey(),
            HuobiDigest.HMAC_SHA_256,
            2,
            HuobiUtils.createUTCDate(exchange.getNonceFactory()),
            signatureCreator);
    return checkResult(result);
  }

  public List<HuobiOrder> getHuobiOrder(String... orderIds) throws IOException {
    List<HuobiOrder> orders = new ArrayList<>();
    for (String orderId : orderIds) {
      HuobiOrderInfoResult orderInfoResult =
          huobi.getOrder(
              orderId,
              exchange.getExchangeSpecification().getApiKey(),
              HuobiDigest.HMAC_SHA_256,
              2,
              HuobiUtils.createUTCDate(exchange.getNonceFactory()),
              signatureCreator);
      orders.add(checkResult(orderInfoResult));
    }
    return orders;
  }

  private String getAccountId() throws IOException {
    return String.valueOf(
        ((HuobiAccountServiceRaw) exchange.getAccountService()).getAccounts()[0].getId());
  }
}
