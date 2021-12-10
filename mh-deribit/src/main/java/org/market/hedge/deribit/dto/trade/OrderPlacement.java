package org.market.hedge.deribit.dto.trade;

import lombok.Data;

import java.util.List;

@Data
public class OrderPlacement {

  private List<Trade> trades;
  private Order order;
}
