package org.market.hedge.deribit.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class DeribitWithdrawalPriority {

  @JsonProperty("value")
  private BigDecimal value;

  @JsonProperty("name")
  private String name;
}
