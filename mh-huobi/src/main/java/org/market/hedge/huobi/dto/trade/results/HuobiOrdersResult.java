package org.market.hedge.huobi.dto.trade.results;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.market.hedge.huobi.dto.HuobiResult;
import org.market.hedge.huobi.dto.trade.HuobiOrder;

public class HuobiOrdersResult extends HuobiResult<HuobiOrder[]> {

  public HuobiOrdersResult(
      @JsonProperty("status") String status,
      @JsonProperty("data") HuobiOrder[] result,
      @JsonProperty("err-code") String errCode,
      @JsonProperty("err-msg") String errMsg) {
    super(status, errCode, errMsg, result);
  }
}
