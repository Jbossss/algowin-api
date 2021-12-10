package org.market.hedge.bibox.dto.trade;

import org.market.hedge.bibox.dto.BiboxCommand;

/** @author odrotleff */
public class BiboxCancelTradeCommand extends BiboxCommand<BiboxCancelTradeCommandBody> {

  public BiboxCancelTradeCommand(String orderId) {
    super("orderpending/cancelTrade", new BiboxCancelTradeCommandBody(orderId));
  }
}
