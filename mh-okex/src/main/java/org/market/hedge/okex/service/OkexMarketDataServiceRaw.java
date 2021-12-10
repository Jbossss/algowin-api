package org.market.hedge.okex.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.market.hedge.core.ParsingCurrencyPair;
import org.market.hedge.okex.dto.marketdata.resp.OkexDepth;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class OkexMarketDataServiceRaw extends OkexBaseService {

    protected OkexMarketDataServiceRaw(Exchange exchange) {
        super(exchange);
    }

    public OrderBook books(ParsingCurrencyPair pair , Integer limit) throws IOException{

        List<OkexDepth> result = checkResult(okex.books(pair.getParsing(),limit.toString()));
        List<LimitOrder> bids =
                result.get(0).getBids().stream()
                        .map(
                                e ->
                                        new LimitOrder(
                                                Order.OrderType.BID, e.get(1), pair.getCurrencyPair(), null, null, e.get(0)))
                        .collect(Collectors.toList());
        List<LimitOrder> asks =
                result.get(0).getAsks().stream()
                        .map(
                                e ->
                                        new LimitOrder(
                                                Order.OrderType.ASK, e.get(1), pair.getCurrencyPair(), null, null, e.get(0)))
                        .collect(Collectors.toList());
        return new OrderBook(new Date(Long.parseLong(result.get(0).getTs())), asks, bids);

    }

}
