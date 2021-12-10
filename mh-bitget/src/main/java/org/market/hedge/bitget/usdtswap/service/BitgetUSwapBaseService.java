package org.market.hedge.bitget.usdtswap.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.service.BaseExchangeService;
import org.knowm.xchange.service.BaseService;
import org.market.hedge.bitget.usdtswap.BitgetUSwapAuthenticated;
import si.mazi.rescu.RestProxyFactory;

public class BitgetUSwapBaseService extends BaseExchangeService implements BaseService {

  protected final String apiKey;
  protected   BitgetUSwapAuthenticated bitget;


  /**
   * Constructor
   *

   */
  protected BitgetUSwapBaseService(Exchange exchange) {
    super(exchange);
    this.bitget =
        RestProxyFactory.createProxy(
                BitgetUSwapAuthenticated.class,
            exchange.getExchangeSpecification().getSslUri());
    this.apiKey = exchange.getExchangeSpecification().getApiKey();


  }


}
