package org.market.hedge.deribit.dto.trade;

import lombok.Data;

import java.util.List;

@Data
public class UserSettlements {

  private List<Settlement> settlements;

  /** Continuation token for pagination. */
  private String continuation;
}
