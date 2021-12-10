package org.market.hedge.bibox.dto.trade;

import org.market.hedge.bibox.dto.BiboxCommand;

/** @author odrotleff */
public class BiboxOrderPendingListCommand extends BiboxCommand<BiboxOrderPendingListCommandBody> {

  public BiboxOrderPendingListCommand(BiboxOrderPendingListCommandBody body) {
    super("orderpending/orderPendingList", body);
  }
}
