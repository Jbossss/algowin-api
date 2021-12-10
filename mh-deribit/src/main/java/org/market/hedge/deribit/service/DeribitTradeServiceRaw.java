package org.market.hedge.deribit.service;

import org.market.hedge.deribit.DeribitExchange;
import org.market.hedge.deribit.dto.DeribitException;
import org.market.hedge.deribit.dto.trade.*;
import org.market.hedge.dto.trade.*;
import si.mazi.rescu.ParamsDigest;

import javax.ws.rs.HeaderParam;
import javax.ws.rs.QueryParam;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class DeribitTradeServiceRaw extends DeribitBaseService {

  public DeribitTradeServiceRaw(DeribitExchange exchange) {
    super(exchange);
  }

  public OrderPlacement buy(
      String instrumentName,
      BigDecimal amount,
      OrderType type,
      String label,
      BigDecimal price,
      TimeInForce timeInForce,
      BigDecimal maxShow,
      Boolean postOnly,
      Boolean reduceOnly,
      BigDecimal stopPrice,
      Trigger trigger,
      AdvancedOptions advanced)
      throws IOException {
    return deribitAuthenticated
        .buy(
            instrumentName,
            amount,
            Objects.nonNull(type)?type.name():null,
            label,
            price,
            timeInForce,
            maxShow,
            postOnly,
            reduceOnly,
            stopPrice,
            trigger,
            advanced,
            deribitAuth)
        .getResult();
  }

  public OrderPlacement sell(
      String instrumentName,
      BigDecimal amount,
      OrderType type,
      String label,
      BigDecimal price,
      TimeInForce timeInForce,
      BigDecimal maxShow,
      Boolean postOnly,
      Boolean reduceOnly,
      BigDecimal stopPrice,
      Trigger trigger,
      AdvancedOptions advanced)
      throws IOException {
    return deribitAuthenticated
        .sell(
            instrumentName,
            amount,
            Objects.nonNull(type)?type.name():null,
            label,
            price,
            timeInForce,
            maxShow,
            postOnly,
            reduceOnly,
            stopPrice,
            trigger,
            advanced,
            deribitAuth)
        .getResult();
  }

  public Order cancel(String orderId) throws IOException {
    return deribitAuthenticated.cancel(orderId, deribitAuth).getResult();
  }

  public List<Order> getOpenOrdersByInstrument(String instrumentName, String type)
      throws IOException {
    return deribitAuthenticated
        .getOpenOrdersByInstrument(instrumentName, type, deribitAuth)
        .getResult();
  }

  public UserTrades getUserTradesByInstrument(
      String instrumentName,
      Integer startSeq,
      Integer endSeq,
      Integer count,
      Boolean includeOld,
      String sorting)
      throws IOException {
    return deribitAuthenticated
        .getUserTradesByInstrument(
            instrumentName, startSeq, endSeq, count, includeOld, sorting, deribitAuth)
        .getResult();
  }

  public UserTrades getUserTradesByInstrumentAndTime(
      String instrumentName,
      Date startTimestsamp,
      Date endTimestamp,
      Integer count,
      Boolean includeOld,
      String sorting)
      throws IOException {
    return deribitAuthenticated
        .getUserTradesByInstrumentAndTime(
            instrumentName,
            startTimestsamp.getTime(),
            endTimestamp.getTime(),
            count,
            includeOld,
            sorting,
            deribitAuth)
        .getResult();
  }

  public UserSettlements getUserSettlementsByInstrument(
      String instrumentName, SettlementType type, Integer count) throws IOException {
    return deribitAuthenticated
        .getSettlementHistoryByInstrument(instrumentName, type, count, deribitAuth)
        .getResult();
  }

  public List<Order> getOrderHistoryByCurrency(String currency,String kind, String type) throws IOException {
    return deribitAuthenticated.getOrderHistoryByCurrency(currency, kind, type, deribitAuth).getResult();
  }


}
