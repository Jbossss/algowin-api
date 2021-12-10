package org.market.hedge.huobi.option.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.exceptions.ExchangeException;
import org.market.hedge.huobi.dto.HuobiResult;
import org.market.hedge.huobi.option.HuobiOption;
import org.market.hedge.huobi.option.dto.HuobiOptionResult;
import org.knowm.xchange.service.BaseExchangeService;
import org.knowm.xchange.service.BaseService;
import si.mazi.rescu.ParamsDigest;
import si.mazi.rescu.RestProxyFactory;

import java.util.Objects;

public class HuobiOptionBaseService extends BaseExchangeService implements BaseService {

    protected HuobiOption huobiOption;
    protected ParamsDigest signatureCreator;

    protected HuobiOptionBaseService(Exchange exchange) {
        super(exchange);
        huobiOption =
                RestProxyFactory.createProxy(
                        HuobiOption.class, exchange.getExchangeSpecification().getSslUri());
        signatureCreator =
                HuobiOptionDigest.createInstance(exchange.getExchangeSpecification().getSecretKey());
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
            if (Objects.isNull(huobiError)||huobiError.length() == 0) {
                throw new ExchangeException("Missing error message");
            } else {
                throw new ExchangeException(huobiError);
            }
        }
        return huobiResult.getData();
    }

    protected Boolean checkResultList(HuobiOptionResult huobiResult) {
        if (!huobiResult.isSuccess()) {
            return false;
        }
        return true;
    }


}
