package org.market.hedge.huobi.coinSwap.service;

import org.knowm.xchange.Exchange;
import org.market.hedge.core.ParsingCurrencyPair;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.exceptions.ExchangeException;
import org.market.hedge.huobi.dto.marketdata.HuobiDepth;
import org.market.hedge.service.marketdata.MHMarketDataService;


import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class HuobiSwapMarketDataService  extends HuobiSwapMarketDataServiceRaw implements MHMarketDataService {


    public HuobiSwapMarketDataService(Exchange exchange) {
        super(exchange);
    }

    @Override
    public OrderBook getOrderBook(ParsingCurrencyPair parsingCurrencyPair, Object... args) throws IOException {
        String depthType = "step0";
        if (args != null && args.length == 1) {
            Object arg0 = args[0];
            if (!(arg0 instanceof String)) {
                throw new ExchangeException("Argument 0 must be an String!");
            } else {
                depthType = (String) arg0;
            }
        }

        HuobiDepth depth = getHuobiDepth(parsingCurrencyPair, depthType);

        List<LimitOrder> bids =
                depth.getBids().entrySet().stream()
                        .map(
                                e ->
                                        new LimitOrder(
                                                Order.OrderType.BID, e.getValue(), parsingCurrencyPair.getCurrencyPair(), null, null, e.getKey()))
                        .collect(Collectors.toList());
        List<LimitOrder> asks =
                depth.getAsks().entrySet().stream()
                        .map(
                                e ->
                                        new LimitOrder(
                                                Order.OrderType.ASK, e.getValue(), parsingCurrencyPair.getCurrencyPair(), null, null, e.getKey()))
                        .collect(Collectors.toList());
        return new OrderBook(depth.getTs(), asks, bids);
    }
}
