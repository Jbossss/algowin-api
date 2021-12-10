package org.market.hedge.okex;

import org.junit.Test;
import org.knowm.xchange.currency.CurrencyPair;
import org.market.hedge.core.Direction;
import org.market.hedge.core.ParsingCurrencyPair;
import org.market.hedge.core.TradingArea;

import java.math.BigDecimal;
import java.util.Date;

public class OkexStreamingParsingTest {

    @Test
    public void instanceOption() {
        OkexStreamingParsing parsing=new OkexStreamingParsing(TradingArea.Option);
        ParsingCurrencyPair parsingCurrencyPair=parsing.instanceOption(CurrencyPair.BTC_USD,new Date(1616745600000L),new BigDecimal("50000"), Direction.Call);
        System.out.println(parsingCurrencyPair.getParsing());
    }

}
