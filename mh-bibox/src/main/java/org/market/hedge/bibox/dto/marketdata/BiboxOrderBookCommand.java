package org.market.hedge.bibox.dto.marketdata;

import org.market.hedge.bibox.dto.BiboxCommand;

public class BiboxOrderBookCommand extends BiboxCommand<BiboxOrderBookCommandBody> {

  public BiboxOrderBookCommand(String pair, Integer size) {
    super("api/depth", new BiboxOrderBookCommandBody(pair, size));
  }
}
