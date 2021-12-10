package org.market.hedge.okex.service;

import com.okex.open.api.config.APIConfiguration;
import com.okex.open.api.enums.I18nEnum;
import com.okex.open.api.service.trade.TradeAPIService;
import com.okex.open.api.service.trade.impl.TradeAPIServiceImpl;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.service.BaseExchangeService;
import org.knowm.xchange.service.BaseService;
import org.market.hedge.okex.Okex;
import org.market.hedge.okex.dto.OkexResult;
import si.mazi.rescu.ParamsDigest;
import si.mazi.rescu.RestProxyFactory;


/**
 * @author chuxianbo
 */
public class OkexBaseService extends BaseExchangeService implements BaseService {

    protected Okex okex;
    protected ParamsDigest signatureCreator;
    protected APIConfiguration config;
    protected TradeAPIService tradeAPIService;

    protected OkexBaseService(Exchange exchange) {
        super(exchange);
        okex = RestProxyFactory.createProxy(
                Okex.class, exchange.getExchangeSpecification().getSslUri());

        config = new APIConfiguration();
        config.setEndpoint("https://www.okex.com/");

        config.setApiKey(exchange.getExchangeSpecification().getApiKey());
        config.setSecretKey(exchange.getExchangeSpecification().getSecretKey());
        config.setPassphrase(exchange.getExchangeSpecification().getUserName());//"pwn2own"
        config.setPrint(true);
        /* config.setI18n(I18nEnum.SIMPLIFIED_CHINESE);*/
        config.setI18n(I18nEnum.ENGLISH);
        tradeAPIService = new TradeAPIServiceImpl(config);
    }

    protected <R> R checkResult(OkexResult<R> okexResult) {
        if (!okexResult.isSuccess()) {
            String huobiError = okexResult.getError();
            if (huobiError.length() == 0) {
                throw new ExchangeException("Missing error message");
            } else {
                throw new ExchangeException(huobiError);
            }
        }
        return okexResult.getData();
    }




}
