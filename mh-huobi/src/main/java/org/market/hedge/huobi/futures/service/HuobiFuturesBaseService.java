package org.market.hedge.huobi.futures.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.service.BaseExchangeService;
import org.knowm.xchange.service.BaseService;
import org.market.hedge.huobi.dto.HuobiResult;
import org.market.hedge.huobi.futures.HuobiFutures;
import org.market.hedge.huobi.option.dto.HuobiOptionResult;
import si.mazi.rescu.ParamsDigest;
import si.mazi.rescu.RestProxyFactory;

public class HuobiFuturesBaseService extends BaseExchangeService implements BaseService {

    protected HuobiFutures huobiFutures;
    protected ParamsDigest signatureCreator;

    protected HuobiFuturesBaseService(Exchange exchange) {
        super(exchange);
        huobiFutures =
                RestProxyFactory.createProxy(
                        HuobiFutures.class, exchange.getExchangeSpecification().getSslUri());
        signatureCreator =
                HuobiFuturesDigest.createInstance(exchange.getExchangeSpecification().getSecretKey());
    }

    protected <R> R checkResult(HuobiResult<R> huobiResult) {
        if (!huobiResult.isSuccess()) {
            String huobiError = huobiResult.getError();
            if (huobiError.length() == 0) {
                throw new ExchangeException("Missing error message");
            } else {
                throw new ExchangeException(huobiError);
            }
        }
        return huobiResult.getResult();
    }

    protected <R> R checkResult(HuobiOptionResult<R> huobiResult) {
        if (!huobiResult.isSuccess()) {
            String huobiError = huobiResult.getError();
            if (huobiError.length() == 0) {
                throw new ExchangeException("Missing error message");
            } else {
                throw new ExchangeException(huobiError);
            }
        }
        return huobiResult.getData();
    }

}
