package org.market.hedge.huobi.dto.marketdata.results;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.market.hedge.huobi.dto.HuobiResult;
import org.market.hedge.huobi.dto.marketdata.HuobiTradeWrapper;

import java.util.Date;

public class HuobiTradesResult extends HuobiResult<HuobiTradeWrapper[]> {
  @JsonCreator
  public HuobiTradesResult(
      @JsonProperty("status") String status,
      @JsonProperty("ts") Date ts,
      @JsonProperty("ch") String ch,
      @JsonProperty("data") HuobiTradeWrapper[] data,
      @JsonProperty("err-code") String errCode,
      @JsonProperty("err-msg") String errMsg) {
    super(status, errCode, errMsg, data);
  }
}
