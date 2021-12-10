package org.market.hedge.bibox.usdtswap.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.service.BaseExchangeService;
import org.knowm.xchange.service.BaseService;
import org.market.hedge.bibox.coinswap.BiboxCoinSwapAuthenticated;
import org.market.hedge.bibox.dto.BiboxResponse;
import org.market.hedge.bibox.service.BiboxDigest;
import org.market.hedge.bibox.usdtswap.BiboxUSDTSwapAuthenticated;
import si.mazi.rescu.ParamsDigest;
import si.mazi.rescu.RestProxyFactory;

public class BiboxUSDTSwapBaseService extends BaseExchangeService implements BaseService {

  protected final String apiKey;
  protected final BiboxUSDTSwapAuthenticated bibox;

  /**
   * Constructor
   *
   * @param exchange
   */
  protected BiboxUSDTSwapBaseService(Exchange exchange) {
    super(exchange);
    this.bibox =
        RestProxyFactory.createProxy(
                BiboxUSDTSwapAuthenticated.class,
            exchange.getExchangeSpecification().getSslUri());
    this.apiKey = exchange.getExchangeSpecification().getApiKey();

  }

  protected static void throwErrors(BiboxResponse<?> response) {
    if (!response.getCmd().equals("depth")){
      if (response.getState() != 0) {
        throw new ExchangeException(
                String.format("Error (state:%s, message:%s)", response.getState(), response.getMsg()));
      }
    }
  }
}
