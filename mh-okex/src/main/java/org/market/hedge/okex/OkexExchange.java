package org.market.hedge.okex;

import org.knowm.xchange.exceptions.ExchangeException;
import org.market.hedge.BaseMHExchange;
import org.market.hedge.MHExchange;
import org.market.hedge.MHExchangeSpecification;
import org.market.hedge.core.TradingArea;
import org.market.hedge.exception.NullTradingAreaException;
import org.market.hedge.okex.service.OkexMarketDataService;
import org.market.hedge.okex.service.OkexTradeService;
import si.mazi.rescu.SynchronizedValueFactory;

import java.io.IOException;

public class OkexExchange extends BaseMHExchange implements MHExchange {

    @Override
    protected void initServices() {
        switch (mHexchangeSpecification.getTradingArea()){
            case Spot:
            case CoinSwap:
            case PerpetualSwap:
            case Option:
                this.mHmarketDataService=new OkexMarketDataService(this);
                this.mHtradeService=new OkexTradeService(this);
                break;
            default:
                throw new NullTradingAreaException(mHexchangeSpecification.getTradingArea());
        }
    }

    @Override
    public MHExchangeSpecification getDefaultExchangeSpecification(TradingArea tradingArea) {
        this.streamingParsing=new OkexStreamingParsing(tradingArea);
        MHExchangeSpecification exchangeSpecification =
                new MHExchangeSpecification(this.getClass().getCanonicalName());
        exchangeSpecification.setTradingArea(tradingArea);
        switch (tradingArea){
            case Spot:
            case CoinSwap:
            case PerpetualSwap:
            case Option:
                exchangeSpecification.setSslUri("https://www.okex.com");
                exchangeSpecification.setHost("www.okex.com");
                exchangeSpecification.setPort(80);
                exchangeSpecification.setExchangeName("okex");
                exchangeSpecification.setExchangeDescription(
                        "Okex");
                return exchangeSpecification;
            default:
                break;
        }
        throw new NullTradingAreaException(tradingArea);
    }

    @Override
    public SynchronizedValueFactory<Long> getNonceFactory() {
        return null;
    }

    @Override
    public void remoteInit() throws IOException, ExchangeException {

    }
}
