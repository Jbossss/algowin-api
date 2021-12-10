package org.market.hedge.bibox.coinswap.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.service.BaseExchangeService;
import org.knowm.xchange.service.BaseService;
import org.market.hedge.bibox.coinswap.BiboxCoinSwapAuthenticated;
import org.market.hedge.bibox.dto.BiboxResponse;
import org.market.hedge.bibox.service.BiboxDigest;
import si.mazi.rescu.ParamsDigest;
import si.mazi.rescu.RestProxyFactory;

public class BiboxCoinSwapBaseService extends BaseExchangeService implements BaseService {

  protected final String apiKey;
  protected final BiboxCoinSwapAuthenticated bibox;
  protected final ParamsDigest signatureCreator;

  /**
   * Constructor
   *
   * @param exchange
   */
  protected BiboxCoinSwapBaseService(Exchange exchange) {
    super(exchange);
    this.bibox =
        RestProxyFactory.createProxy(
                BiboxCoinSwapAuthenticated.class,
            exchange.getExchangeSpecification().getSslUri());
    this.apiKey = exchange.getExchangeSpecification().getApiKey();
    this.signatureCreator =
        BiboxDigest.createInstance(exchange.getExchangeSpecification().getSecretKey());
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
