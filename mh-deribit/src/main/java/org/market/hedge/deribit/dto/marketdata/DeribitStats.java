package org.market.hedge.deribit.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class DeribitStats {

  /** volume during last 24h in base currency */
  @JsonProperty("volume")
  private BigDecimal volume;

  /** lowest price during 24h */
  @JsonProperty("low")
  private BigDecimal low;

  /** highest price during 24h */
  @JsonProperty("high")
  private BigDecimal high;
}
