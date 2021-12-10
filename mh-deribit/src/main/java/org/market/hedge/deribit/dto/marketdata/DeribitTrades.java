package org.market.hedge.deribit.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class DeribitTrades {

  @JsonProperty("trades")
  private List<DeribitTrade> trades;

  @JsonProperty("has_more")
  private boolean hasMore;
}
