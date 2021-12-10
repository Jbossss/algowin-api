package org.market.hedge.binance.dto.meta;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class BinanceTime {

  @JsonProperty private long serverTime;

  public Date getServerTime() {
    return new Date(serverTime);
  }
}
