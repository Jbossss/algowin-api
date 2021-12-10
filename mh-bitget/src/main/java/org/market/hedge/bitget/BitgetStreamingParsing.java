package org.market.hedge.bitget;

import org.knowm.xchange.currency.CurrencyPair;
import org.market.hedge.core.ParsingCurrencyPair;
import org.market.hedge.core.StreamingParsing;
import org.market.hedge.core.TradingArea;

public class BitgetStreamingParsing extends StreamingParsing {

    public BitgetStreamingParsing(TradingArea tradingArea) {
        super(tradingArea);
    }

    @Override
    public ParsingCurrencyPair instancePerpetualSwap(CurrencyPair currencyPair, Object... args) {
        String parsing = "cmt_"+currencyPair.base.toString().toLowerCase()+currencyPair.counter.toString().toLowerCase();
        return new ParsingCurrencyPair(parsing,currencyPair, args);
    }


    public String getCurrencyPair(CurrencyPair currencyPair) {
        String parsing = "cmt_"+currencyPair.base.toString().toLowerCase()+currencyPair.counter.toString().toLowerCase();
        return parsing;
    }
}
