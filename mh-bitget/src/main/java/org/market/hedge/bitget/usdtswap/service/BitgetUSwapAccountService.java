package org.market.hedge.bitget.usdtswap.service;

import org.knowm.xchange.Exchange;
import org.market.hedge.core.BilateralPositionInfo;
import org.market.hedge.core.ParsingCurrencyPair;
import org.market.hedge.service.account.MHAccountService;

import java.io.IOException;

/** @author odrotleff */
public class BitgetUSwapAccountService extends BitgetUSwapAccountServiceRaw implements MHAccountService {

  public BitgetUSwapAccountService(Exchange exchange) {
    super(exchange);
  }

  @Override
  public BilateralPositionInfo getBilateralPosition(ParsingCurrencyPair parsingCurrencyPair, Object... args) throws IOException {
    return null;
  }
}
