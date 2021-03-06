package org.market.hedge.huobi.dto.account.results;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.market.hedge.huobi.dto.HuobiResult;
import org.market.hedge.huobi.dto.account.HuobiDepositAddressWithTag;

public class HuobiDepositAddressWithTagResult extends HuobiResult<HuobiDepositAddressWithTag> {
  @JsonCreator
  public HuobiDepositAddressWithTagResult(
      @JsonProperty("status") String status,
      @JsonProperty("data") HuobiDepositAddressWithTag depositAddressWithTag,
      @JsonProperty("err-code") String errCode,
      @JsonProperty("err-msg") String errMsg) {
    super(status, errCode, errMsg, depositAddressWithTag);
  }
}
