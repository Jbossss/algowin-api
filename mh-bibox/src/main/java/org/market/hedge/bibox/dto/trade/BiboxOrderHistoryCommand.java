package org.market.hedge.bibox.dto.trade;

import org.market.hedge.bibox.dto.BiboxCommand;

/** @author odrotleff */
public class BiboxOrderHistoryCommand extends BiboxCommand<BiboxOrderPendingListCommandBody> {

  public BiboxOrderHistoryCommand(BiboxOrderPendingListCommandBody body) {
    super("orderpending/orderHistoryList", body);
  }
}
