package org.market.hedge.core;

import org.knowm.xchange.currency.CurrencyPair;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

public class Kline {

  private  CurrencyPair pair;
  private  String parsing;
  private  KlineInterval interval;
  private  long openTime;
  private  BigDecimal open;
  private  BigDecimal high;
  private  BigDecimal low;
  private  BigDecimal close;
  private  BigDecimal volume;
  private  long closeTime;
  private  BigDecimal quoteAssetVolume;
  private  long numberOfTrades;
  private  BigDecimal takerBuyBaseAssetVolume;
  private  BigDecimal takerBuyQuoteAssetVolume;

  public Kline(CurrencyPair pair,String parsing, KlineInterval interval, long openTime, BigDecimal open, BigDecimal high, BigDecimal low, BigDecimal close, BigDecimal volume, long closeTime, BigDecimal quoteAssetVolume, long numberOfTrades, BigDecimal takerBuyBaseAssetVolume, BigDecimal takerBuyQuoteAssetVolume) {
    this.parsing=parsing;
    this.pair = pair;
    this.interval = interval;
    this.openTime = openTime;
    this.open = open;
    this.high = high;
    this.low = low;
    this.close = close;
    this.volume = volume;
    this.closeTime = closeTime;
    this.quoteAssetVolume = quoteAssetVolume;
    this.numberOfTrades = numberOfTrades;
    this.takerBuyBaseAssetVolume = takerBuyBaseAssetVolume;
    this.takerBuyQuoteAssetVolume = takerBuyQuoteAssetVolume;
  }

  public Kline(){}


  public BigDecimal getAveragePrice() {
    return low.add(high).divide(new BigDecimal("2"));
  }

  public CurrencyPair getPair() {
    return pair;
  }

  public String getParsing() {
    return parsing;
  }

  public KlineInterval getInterval() {
    return interval;
  }

  public long getOpenTime() {
    return openTime;
  }

  public BigDecimal getOpen() {
    return open;
  }

  public BigDecimal getHigh() {
    return high;
  }

  public BigDecimal getLow() {
    return low;
  }

  public BigDecimal getClose() {
    return close;
  }

  public BigDecimal getVolume() {
    return volume;
  }

  public long getCloseTime() {
    return closeTime;
  }

  public BigDecimal getQuoteAssetVolume() {
    return quoteAssetVolume;
  }

  public long getNumberOfTrades() {
    return numberOfTrades;
  }

  public BigDecimal getTakerBuyBaseAssetVolume() {
    return takerBuyBaseAssetVolume;
  }

  public BigDecimal getTakerBuyQuoteAssetVolume() {
    return takerBuyQuoteAssetVolume;
  }

  public String toString() {
    String tstamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(openTime);
    return String.format(
        "[%s] %s %s O:%.6f A:%.6f C:%.6f", pair, tstamp, interval, open, getAveragePrice(), close);
  }
}
