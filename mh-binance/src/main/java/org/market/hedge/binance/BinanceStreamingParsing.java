package org.market.hedge.binance;

import org.knowm.xchange.currency.CurrencyPair;
import org.market.hedge.core.Direction;
import org.market.hedge.core.ParsingCurrencyPair;
import org.market.hedge.core.StreamingParsing;
import org.market.hedge.core.TradingArea;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BinanceStreamingParsing extends StreamingParsing {

    public BinanceStreamingParsing(TradingArea tradingArea) {
        super(tradingArea);
    }

    @Override
    public ParsingCurrencyPair instanceSpot(CurrencyPair currencyPair, Object... args) {
        return super.instanceSpot(currencyPair, args);
    }

    @Override
    public ParsingCurrencyPair instancePerpetualSwap(CurrencyPair currencyPair, Object... args) {
        return new ParsingCurrencyPair(currencyPair.base.toString().toUpperCase()+currencyPair.counter.toString().toUpperCase() ,currencyPair, args);
    }

    /**
     * eg: BTC-200730-9000-C
     *
     *      @param args args[0] 期权到期日  true  Date
     *                  args[1] Strike  true  BigDecimal
     *                  args[2] 看跌Put或看涨Call  true  Direction  -1 = Put, 1 = Call
     * */
    @Override
    public ParsingCurrencyPair instanceOption(CurrencyPair currencyPair, Object... args) {
        String[] strNow1 = new SimpleDateFormat("yyyy-MM-dd").format((Date) args[0]).toString().split("-");
        BigDecimal strike=((BigDecimal)args[1]).setScale(0,BigDecimal.ROUND_DOWN);
        Direction direction=(Direction)args[2];
        return new ParsingCurrencyPair((currencyPair.base+"-"
                +(strNow1[0].substring(strNow1[0].length()-2,strNow1[0].length()))
                +(Integer.parseInt(strNow1[1])>9?Integer.parseInt(strNow1[1]):("0"+Integer.parseInt(strNow1[1])))
                +strNow1[2]
                +"-"+strike
                +"-"+direction.name().charAt(0)),currencyPair,args);

    }

    /**
     * eg: BTCUSDT_210625
     *
     *      @param args args[0] 期权到期日  true  时间戳
     *
     * */
    @Override
    public ParsingCurrencyPair instanceFutures(CurrencyPair currencyPair, Object... args) {
        String st=currencyPair.base.toString().toUpperCase()+currencyPair.counter.toString().toUpperCase();
        String[] strNow1 = new SimpleDateFormat("yyyy-MM-dd").format(new Date((Long) args[0])).toString().split("-");
        String strNow=(strNow1[0].substring(strNow1[0].length()-2,strNow1[0].length()))
                +(Integer.parseInt(strNow1[1])>9?Integer.parseInt(strNow1[1]):("0"+Integer.parseInt(strNow1[1])))
                +strNow1[2];

        return new ParsingCurrencyPair(st+"_"+strNow,currencyPair,args);
    }

    public static void main(String[] args) {
        String[] strNow1 = new SimpleDateFormat("yyyy-MM-dd").format(new Date(1618502400000L)).toString().split("-");
        String strNow=(strNow1[0].substring(strNow1[0].length()-2,strNow1[0].length()))
                +(Integer.parseInt(strNow1[1])>9?Integer.parseInt(strNow1[1]):("0"+Integer.parseInt(strNow1[1])))
                +strNow1[2];

        System.out.println(strNow);
    }
}
