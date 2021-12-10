package org.market.hedge.okex;

import org.knowm.xchange.currency.CurrencyPair;
import org.market.hedge.core.Direction;
import org.market.hedge.core.ParsingCurrencyPair;
import org.market.hedge.core.StreamingParsing;
import org.market.hedge.core.TradingArea;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * SPOT：币币
 * SWAP：永续合约
 * FUTURES：交割合约
 * OPTION：期权
 * @author chuxianbo
 * */
public class OkexStreamingParsing extends StreamingParsing {

    private static final Logger logger= LoggerFactory.getLogger(OkexStreamingParsing.class);

    public OkexStreamingParsing(TradingArea tradingArea) {
        super(tradingArea);
    }


    @Override
    public ParsingCurrencyPair instanceSpot(CurrencyPair currencyPair, Object... args) {
        return new ParsingCurrencyPair(currencyPair.base.toString()+"-"+currencyPair.counter.toString()+"-SPOT",currencyPair, args);
    }

    /**
     * BTC-USD-SWAP
     * */
    @Override
    public ParsingCurrencyPair instancePerpetualSwap(CurrencyPair currencyPair, Object... args) {
        return new ParsingCurrencyPair(currencyPair.base.toString()+"-"+currencyPair.counter.toString()+"-SWAP",currencyPair, args);
    }

    @Override
    public ParsingCurrencyPair instanceCoinSwap(CurrencyPair currencyPair, Object... args) {
        return new ParsingCurrencyPair(currencyPair.base.toString()+"-"+currencyPair.counter.toString()+"-SWAP",currencyPair, args);
    }

    /**
     * eg: BTC-USD-211008-40000-C
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
        return new ParsingCurrencyPair((currencyPair.base+"-"+currencyPair.counter
                +"-"+(strNow1[0].substring(strNow1[0].length()-2,strNow1[0].length()))
                +(Integer.parseInt(strNow1[1])>9?Integer.parseInt(strNow1[1]):("0"+Integer.parseInt(strNow1[1])))
                +strNow1[2]
                +"-"+strike
                +"-"+direction.name().substring(0,1)),currencyPair,args);

    }
}
