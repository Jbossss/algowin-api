package org.market.hedge.huobi.dto.marketdata.results;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.market.hedge.huobi.dto.HuobiResult;
import org.market.hedge.huobi.dto.marketdata.HuobiDepth;

import java.util.Date;

public class HuobiDepthResult extends HuobiResult<HuobiDepth> {

  @JsonCreator
  public HuobiDepthResult(
      @JsonProperty("status") String status,
      @JsonProperty("ts") Date ts,
      @JsonProperty("tick") HuobiDepth tick,
      @JsonProperty("ch") String ch,
      @JsonProperty("err-code") String errCode,
      @JsonProperty("err-msg") String errMsg) {
    super(status, errCode, errMsg, tick);
  }
}
