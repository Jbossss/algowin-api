package org.market.hedge.binance.service;

import org.market.hedge.binance.BinanceAdapters;
import org.market.hedge.binance.BinanceAuthenticated;
import org.market.hedge.binance.BinanceExchange;
import org.market.hedge.binance.dto.marketdata.*;
import org.knowm.xchange.client.ResilienceRegistries;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.utils.StreamUtils;
import org.market.hedge.core.KlineInterval;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static org.market.hedge.binance.BinanceResilience.REQUEST_WEIGHT_RATE_LIMITER;

public class BinanceMarketDataServiceRaw extends BinanceBaseService {

  protected BinanceMarketDataServiceRaw(
      BinanceExchange exchange,
      BinanceAuthenticated binance,
      ResilienceRegistries resilienceRegistries) {
    super(exchange, binance, resilienceRegistries);
  }

  public void ping() throws IOException {
    decorateApiCall(() -> binance.ping())
        .withRateLimiter(rateLimiter(REQUEST_WEIGHT_RATE_LIMITER))
        .call();
  }

  public BinanceOrderbook getBinanceOrderbook(CurrencyPair pair, Integer limit) throws IOException {
    return decorateApiCall(() -> binance.depth(BinanceAdapters.toSymbol(pair), limit))
        .withRetry(retry("depth"))
        .withRateLimiter(rateLimiter(REQUEST_WEIGHT_RATE_LIMITER), depthPermits(limit))
        .call();
  }

  public List<BinanceAggTrades> aggTrades(
      CurrencyPair pair, Long fromId, Long startTime, Long endTime, Integer limit)
      throws IOException {
    return decorateApiCall(
            () ->
                binance.aggTrades(
                    BinanceAdapters.toSymbol(pair), fromId, startTime, endTime, limit))
        .withRetry(retry("aggTrades"))
        .withRateLimiter(rateLimiter(REQUEST_WEIGHT_RATE_LIMITER), aggTradesPermits(limit))
        .call();
  }

  public BinanceKline lastKline(CurrencyPair pair, KlineInterval interval) throws IOException {
    return klines(pair, interval, 1, null, null).stream().collect(StreamUtils.singletonCollector());
  }

  public List<BinanceKline> klines(CurrencyPair pair, KlineInterval interval) throws IOException {
    return klines(pair, interval, null, null, null);
  }

  public List<BinanceKline> klines(
      CurrencyPair pair, KlineInterval interval, Integer limit, Long startTime, Long endTime)
      throws IOException {
    List<Object[]> raw =
        decorateApiCall(
                () ->
                    binance.klines(
                        BinanceAdapters.toSymbol(pair), interval.code(), limit, startTime, endTime))
            .withRetry(retry("klines"))
            .withRateLimiter(rateLimiter(REQUEST_WEIGHT_RATE_LIMITER))
            .call();
    return raw.stream()
        .map(obj -> new BinanceKline(pair, interval, obj))
        .collect(Collectors.toList());
  }

  public List<BinanceTicker24h> ticker24h() throws IOException {
    return decorateApiCall(() -> binance.ticker24h())
        .withRetry(retry("ticker24h"))
        .withRateLimiter(rateLimiter(REQUEST_WEIGHT_RATE_LIMITER), 5)
        .call();
  }

  public BinanceTicker24h ticker24h(CurrencyPair pair) throws IOException {
    BinanceTicker24h ticker24h =
        decorateApiCall(() -> binance.ticker24h(BinanceAdapters.toSymbol(pair)))
            .withRetry(retry("ticker24h"))
            .withRateLimiter(rateLimiter(REQUEST_WEIGHT_RATE_LIMITER))
            .call();
    ticker24h.setCurrencyPair(pair);
    return ticker24h;
  }

  public BinancePrice tickerPrice(CurrencyPair pair) throws IOException {
    return tickerAllPrices().stream()
        .filter(p -> p.getCurrencyPair().equals(pair))
        .collect(StreamUtils.singletonCollector());
  }

  public List<BinancePrice> tickerAllPrices() throws IOException {
    return decorateApiCall(() -> binance.tickerAllPrices())
        .withRetry(retry("tickerAllPrices"))
        .withRateLimiter(rateLimiter(REQUEST_WEIGHT_RATE_LIMITER))
        .call();
  }

  public List<BinancePriceQuantity> tickerAllBookTickers() throws IOException {
    return decorateApiCall(() -> binance.tickerAllBookTickers())
        .withRetry(retry("tickerAllBookTickers"))
        .withRateLimiter(rateLimiter(REQUEST_WEIGHT_RATE_LIMITER))
        .call();
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

  protected int aggTradesPermits(Integer limit) {
    if (limit != null && limit > 500) {
      return 2;
    }
    return 1;
  }
}
