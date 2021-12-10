package org.market.hedge.binance.option.service;

import org.knowm.xchange.client.ResilienceRegistries;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.market.hedge.binance.BinanceExchange;
import org.market.hedge.binance.dto.marketdata.BinanceOrderbook;
import org.market.hedge.binance.option.BinanceOptionAuthenticated;
import org.market.hedge.core.Kline;
import org.market.hedge.core.KlineInterval;
import org.market.hedge.core.ParsingCurrencyPair;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static org.market.hedge.binance.BinanceResilience.REQUEST_WEIGHT_RATE_LIMITER;

public class BinanceOptionMarketDataServiceRaw extends BinanceOptionBaseService {
    protected BinanceOptionMarketDataServiceRaw(BinanceExchange exchange, BinanceOptionAuthenticated binance, ResilienceRegistries resilienceRegistries) {
        super(exchange, binance, resilienceRegistries);
    }


    public BinanceOrderbook getBinanceOrderbook(ParsingCurrencyPair pair, Integer limit) throws IOException {
        return decorateApiCall(() -> binance.depth(pair.getParsing(), limit))
                .withRetry(retry("depth"))
                .withRateLimiter(rateLimiter(REQUEST_WEIGHT_RATE_LIMITER), depthPermits(limit))
                .call();
    }

    public static OrderBook convertOrderBook(BinanceOrderbook ob, CurrencyPair pair) {
        List<LimitOrder> bids =
                ob.bids.entrySet().stream()
                        .map(e -> new LimitOrder(Order.OrderType.BID, e.getValue(), pair, null, null, e.getKey()))
                        .collect(Collectors.toList());
        List<LimitOrder> asks =
                ob.asks.entrySet().stream()
                        .map(e -> new LimitOrder(Order.OrderType.ASK, e.getValue(), pair, null, null, e.getKey()))
                        .collect(Collectors.toList());
        return new OrderBook(null, asks, bids);
    }

    protected int depthPermits(Integer limit) {
        if (limit == null || limit <= 100) {
            return 1;
        } else if (limit <= 500) {
            return 5;
        } else if (limit <= 1000) {
            return 10;
        }
        return 50;
    }

    public List<Kline> klines(
            ParsingCurrencyPair pair, KlineInterval interval, Integer limit, Long startTime, Long endTime)
            throws IOException {
        List<Object[]> raw =
                decorateApiCall(
                        () ->
                                binance.klines(
                                        pair.getParsing(), interval.code(), limit, startTime, endTime))
                        .withRetry(retry("klines"))
                        .withRateLimiter(rateLimiter(REQUEST_WEIGHT_RATE_LIMITER))
                        .call();
        return raw.stream()
                .map(obj -> newKline(pair, interval, obj))
                .collect(Collectors.toList());
    }

    public Kline newKline(ParsingCurrencyPair pair, KlineInterval interval, Object[] obj) {
        return new Kline(
                pair.getCurrencyPair(),
                pair.getParsing(),
                interval,
                Long.valueOf(obj[0].toString()),
                new BigDecimal(obj[1].toString()),
                new BigDecimal(obj[2].toString()),
                new BigDecimal(obj[3].toString()),
                new BigDecimal(obj[4].toString()),
                new BigDecimal(obj[5].toString()),
                Long.valueOf(obj[6].toString()),
                new BigDecimal(obj[7].toString()),
                Long.valueOf(obj[8].toString()),
                new BigDecimal(obj[9].toString()),
                new BigDecimal(obj[10].toString()));
    }



}
