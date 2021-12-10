package org.market.hedge;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeSpecification;
import org.market.hedge.core.TradingArea;

public class MHExchangeSpecification extends ExchangeSpecification {

    private TradingArea tradingArea;

    public TradingArea getTradingArea() {
        return tradingArea;
    }

    public void setTradingArea(TradingArea tradingArea) {
        this.tradingArea = tradingArea;
    }

    public MHExchangeSpecification(String exchangeClassName) {
        super(exchangeClassName);
    }

    public MHExchangeSpecification(Class<? extends Exchange> exchangeClass) {
        super(exchangeClass);
    }
}
