package org.market.hedge.coin58;

import org.knowm.xchange.BaseExchange;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeSpecification;
import si.mazi.rescu.SynchronizedValueFactory;

public class Coin58Exchange extends BaseExchange implements Exchange {

    protected void initServices() {

    }

    public SynchronizedValueFactory<Long> getNonceFactory() {
        return null;
    }


    public ExchangeSpecification getDefaultExchangeSpecification() {
        return null;
    }
}
