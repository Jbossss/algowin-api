package org.market.hedge.core;

import org.knowm.xchange.currency.CurrencyPair;
import org.market.hedge.exception.NullTradingAreaException;
import org.market.hedge.service.StreamingParsingCurrencyPair;

public class StreamingParsing {

    public StreamingParsingCurrencyPair parsingCurrencyPair = null;

    public StreamingParsing(TradingArea tradingArea) {
        switch (tradingArea){
            case Futures:
                parsingCurrencyPair= this::instanceFutures;
                break;
            case PerpetualSwap:
                parsingCurrencyPair= this::instancePerpetualSwap;
                break;
            case Option:
                parsingCurrencyPair= this::instanceOption;
                break;
            case Spot:
                parsingCurrencyPair= this::instanceSpot;
                break;
            case Margin:
                parsingCurrencyPair= this::instanceMargin;
                break;
            case CoinSwap:
                parsingCurrencyPair= this::instanceCoinSwap;
                break;
            default:
                break;
        }
    }

    public ParsingCurrencyPair instanceFutures(CurrencyPair currencyPair, Object... args) {
        throw new NullTradingAreaException(TradingArea.Futures);
    }

    public ParsingCurrencyPair instancePerpetualSwap(CurrencyPair currencyPair,Object... args) {
        throw new NullTradingAreaException(TradingArea.PerpetualSwap);
    }

    /**
     * @param args args[0] 期权到期日  true  Date
     *             args[1] Strike  true  integer
     *             args[2] 看跌Put或看涨Call  true  integer  -1 = Put, 1 = Call
     * */
    public ParsingCurrencyPair instanceOption(CurrencyPair currencyPair,Object... args) {
        throw new NullTradingAreaException(TradingArea.Option);
    }

    public ParsingCurrencyPair instanceSpot(CurrencyPair currencyPair,Object... args) {
        throw new NullTradingAreaException(TradingArea.Spot);
    }

    public ParsingCurrencyPair instanceMargin(CurrencyPair currencyPair,Object... args) {
        throw new NullTradingAreaException(TradingArea.Margin);
    }

    /**
     * 币本位解析
    * */
    public ParsingCurrencyPair instanceCoinSwap(CurrencyPair currencyPair,Object... args) {
        throw new NullTradingAreaException(TradingArea.Margin);
    }

}
