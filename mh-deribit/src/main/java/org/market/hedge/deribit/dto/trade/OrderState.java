package org.market.hedge.deribit.dto.trade;

public enum OrderState {
  open,
  filled,
  rejected,
  cancelled,
  untriggered,
  archive;
}
