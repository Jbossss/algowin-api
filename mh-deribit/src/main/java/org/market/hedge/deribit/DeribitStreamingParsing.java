package org.market.hedge.deribit;

import org.knowm.xchange.currency.CurrencyPair;
import org.market.hedge.core.Direction;
import org.market.hedge.core.ParsingCurrencyPair;
import org.market.hedge.core.StreamingParsing;
import org.market.hedge.core.TradingArea;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DeribitStreamingParsing extends StreamingParsing {
    public DeribitStreamingParsing(TradingArea tradingArea) {
        super(tradingArea);
    }

    @Override
    public ParsingCurrencyPair instancePerpetualSwap(CurrencyPair currencyPair, Object... args) {
        return new ParsingCurrencyPair("",currencyPair);
    }

    /**
     * eg: "BTC-1JAN21-21000-C"
     *
     *      @param args args[0] 期权到期日  true  Date
     *                  args[1] Strike  true  BigDecimal
     *                  args[2] 看跌Put或看涨Call  true  Direction  -1 = Put, 1 = Call
     *
     * */
    @Override
    public ParsingCurrencyPair instanceOption(CurrencyPair currencyPair, Object... args) {

        String strNow1 = new SimpleDateFormat("d", Locale.ENGLISH).format((Date) args[0]) +
                new SimpleDateFormat("MMM", Locale.ENGLISH).format((Date) args[0]).toUpperCase() +
                new SimpleDateFormat("yy", Locale.ENGLISH).format((Date) args[0]) ;

        BigDecimal strike=((BigDecimal)args[1]).setScale(0,BigDecimal.ROUND_DOWN);
        Direction direction=(Direction)args[2];
        return new ParsingCurrencyPair(
                (currencyPair.base.toString()+"-"+strNow1+"-"+strike+"-"+(direction==Direction.Call?"C":"P")
                ),currencyPair,args);
    }
}
