package org.market.hedge.bibox.dto.trade;

import java.math.BigDecimal;
import org.market.hedge.bibox.dto.BiboxCommand;

/** @author odrotleff */
public class BiboxTradeCommand extends BiboxCommand<BiboxTradeCommandBody> {

  public BiboxTradeCommand(
      String pair,
      int accountType,
      int orderType,
      int orderSide,
      boolean payBix,
      BigDecimal price,
      BigDecimal amount,
      BigDecimal money) {
    super(
        "orderpending/trade",
        new BiboxTradeCommandBody(
            pair, accountType, orderType, orderSide, payBix, price, amount, money));
  }
}
