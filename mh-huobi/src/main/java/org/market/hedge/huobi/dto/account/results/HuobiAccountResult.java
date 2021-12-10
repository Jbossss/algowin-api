package org.market.hedge.huobi.dto.account.results;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.market.hedge.huobi.dto.HuobiResult;
import org.market.hedge.huobi.dto.account.HuobiAccount;

public class HuobiAccountResult extends HuobiResult<HuobiAccount[]> {

  public HuobiAccountResult(
      @JsonProperty("status") String status,
      @JsonProperty("data") HuobiAccount[] result,
      @JsonProperty("err-code") String errCode,
      @JsonProperty("err-msg") String errMsg) {
    super(status, errCode, errMsg, result);
  }
}
