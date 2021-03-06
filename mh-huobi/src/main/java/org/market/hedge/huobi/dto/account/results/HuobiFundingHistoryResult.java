package org.market.hedge.huobi.dto.account.results;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.market.hedge.huobi.dto.HuobiResult;
import org.market.hedge.huobi.dto.account.HuobiFundingRecord;

public class HuobiFundingHistoryResult extends HuobiResult<HuobiFundingRecord[]> {

  public HuobiFundingHistoryResult(
      @JsonProperty("status") String status,
      @JsonProperty("data") HuobiFundingRecord[] result,
      @JsonProperty("err-code") String errCode,
      @JsonProperty("err-msg") String errMsg) {
    super(status, errCode, errMsg, result);
  }
}
