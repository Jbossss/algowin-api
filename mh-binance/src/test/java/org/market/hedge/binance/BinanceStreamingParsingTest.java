package org.market.hedge.binance;

import org.junit.Test;
import org.knowm.xchange.currency.CurrencyPair;
import org.market.hedge.core.Direction;
import org.market.hedge.core.ParsingCurrencyPair;
import org.market.hedge.core.TradingArea;

import java.math.BigDecimal;
import java.util.Date;

public class BinanceStreamingParsingTest {

    @Test
    public void instanceOption(String[] args) {
        BinanceStreamingParsing parsing=new BinanceStreamingParsing(TradingArea.Option);
        ParsingCurrencyPair parsingCurrencyPair=parsing.instanceOption(CurrencyPair.BTC_USDT,new Date(1616745600000L),new BigDecimal("50000"), Direction.Call);
        System.out.println(parsingCurrencyPair.getParsing());
    }

}
