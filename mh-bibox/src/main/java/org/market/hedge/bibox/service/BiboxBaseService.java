package org.market.hedge.bibox.service;

import org.knowm.xchange.Exchange;
import org.market.hedge.bibox.BiboxAuthenticated;
import org.market.hedge.bibox.dto.BiboxResponse;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.service.BaseExchangeService;
import org.knowm.xchange.service.BaseService;
import si.mazi.rescu.ParamsDigest;
import si.mazi.rescu.RestProxyFactory;

public class BiboxBaseService extends BaseExchangeService implements BaseService {

  protected final String apiKey;
  protected final BiboxAuthenticated bibox;
  protected final ParamsDigest signatureCreator;

  /**
   * Constructor
   *
   * @param exchange
   */
  protected BiboxBaseService(Exchange exchange) {
    super(exchange);
    this.bibox =
        RestProxyFactory.createProxy(
            BiboxAuthenticated.class,
            exchange.getExchangeSpecification().getSslUri());
    this.apiKey = exchange.getExchangeSpecification().getApiKey();
    this.signatureCreator =
        BiboxDigest.createInstance(exchange.getExchangeSpecification().getSecretKey());
  }

  protected static void throwErrors(BiboxResponse<?> response) {
    if (response.getError() != null) {
      throw new ExchangeException(
          response.getError().getCode() + ": " + response.getError().getMsg());
    }
  }
}
