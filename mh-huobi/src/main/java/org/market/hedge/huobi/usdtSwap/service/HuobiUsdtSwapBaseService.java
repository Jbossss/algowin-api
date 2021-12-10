package org.market.hedge.huobi.usdtSwap.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.service.BaseExchangeService;
import org.knowm.xchange.service.BaseService;
import org.market.hedge.huobi.dto.HuobiResult;
import org.market.hedge.huobi.option.dto.HuobiOptionResult;
import org.market.hedge.huobi.usdtSwap.HuobiUsdtSwap;
import si.mazi.rescu.ParamsDigest;
import si.mazi.rescu.RestProxyFactory;

public class HuobiUsdtSwapBaseService extends BaseExchangeService implements BaseService {

    protected HuobiUsdtSwap huobiSwap;
    protected ParamsDigest signatureCreator;

    protected HuobiUsdtSwapBaseService(Exchange exchange) {
        super(exchange);
        huobiSwap =
                RestProxyFactory.createProxy(
                        HuobiUsdtSwap.class, exchange.getExchangeSpecification().getSslUri());
        signatureCreator =
                HuobiUsdtSwapDigest.createInstance(exchange.getExchangeSpecification().getSecretKey());
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
