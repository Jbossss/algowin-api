package org.market.hedge.bitget;

import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.utils.nonce.CurrentTimeNonceFactory;
import org.market.hedge.BaseMHExchange;
import org.market.hedge.MHExchange;
import org.market.hedge.MHExchangeSpecification;
import org.market.hedge.bitget.usdtswap.service.BitgetUSwapAccountService;
import org.market.hedge.bitget.usdtswap.service.BitgetUSwapMarketDataService;
import org.market.hedge.bitget.usdtswap.service.BitgetUSwapTradeService;
import org.market.hedge.core.TradingArea;
import org.market.hedge.exception.NullTradingAreaException;
import si.mazi.rescu.SynchronizedValueFactory;

import java.io.IOException;

public class BitgetExchange extends BaseMHExchange implements MHExchange {


    private final SynchronizedValueFactory<Long> nonceFactory = new CurrentTimeNonceFactory();

    @Override
    protected void initServices() {
        switch (mHexchangeSpecification.getTradingArea()){
            case Spot:
                break;
            case Margin:
                break;
            case PerpetualSwap:
                this.mHmarketDataService= new BitgetUSwapMarketDataService(this);
                this.mHtradeService= new BitgetUSwapTradeService(this);
                this.mHaccountService= new BitgetUSwapAccountService(this);
                break;
            case Futures:
                break;
            case Option:
                break;
            case CoinSwap:
                break;
            default:
                throw new NullTradingAreaException(mHexchangeSpecification.getTradingArea());
        }

    }

    @Override
    public MHExchangeSpecification getDefaultExchangeSpecification(TradingArea tradingArea) {
        this.streamingParsing=new BitgetStreamingParsing(tradingArea);
        MHExchangeSpecification exchangeSpecification =
                new MHExchangeSpecification(this.getClass().getCanonicalName());
        exchangeSpecification.setTradingArea(tradingArea);
        switch (tradingArea){
            case PerpetualSwap:
                exchangeSpecification.setSslUri("https://capi.bitget.com");
                exchangeSpecification.setHost("capi.bitget.com");
                exchangeSpecification.setPort(80);
                exchangeSpecification.setExchangeName("Bitget");
                exchangeSpecification.setExchangeDescription(
                        "Bitget");
                return exchangeSpecification;
            default:
                break;
        }
        throw new NullTradingAreaException(tradingArea);
    }

    @Override
    public SynchronizedValueFactory<Long> getNonceFactory() {
        return nonceFactory;
    }


    @Override
    public void remoteInit() throws IOException, ExchangeException {
        switch (mHexchangeSpecification.getTradingArea()){
            case PerpetualSwap:
            default:
                break;
        }
    }

    @Override
    public void applySpecification(MHExchangeSpecification exchangeSpecification) {
        super.applySpecification(exchangeSpecification);
    }


}

