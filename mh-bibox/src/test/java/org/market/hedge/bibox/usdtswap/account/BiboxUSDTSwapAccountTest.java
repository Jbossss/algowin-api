package org.market.hedge.bibox.usdtswap.account;

import org.junit.Test;
import org.knowm.xchange.currency.CurrencyPair;
import org.market.hedge.MHExchange;
import org.market.hedge.MHExchangeFactory;
import org.market.hedge.bibox.BiboxExchange;
import org.market.hedge.core.BilateralPositionInfo;
import org.market.hedge.core.TradingArea;
import org.market.hedge.service.StreamingParsingCurrencyPair;
import org.market.hedge.service.account.MHAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class BiboxUSDTSwapAccountTest {

    private static Logger logger= LoggerFactory.getLogger(BiboxUSDTSwapAccountTest.class);


    @Test
    public void getBilateralPosition() throws IOException {
        MHExchange bibox= MHExchangeFactory.INSTANCE.createExchange(BiboxExchange.class,
                "00064ff6ec177438e289d5559b0693efc505af65",
                "b53766db065a9413f572ad75b13f5464acbb75a0",
                TradingArea.PerpetualSwap);
        StreamingParsingCurrencyPair parsing=bibox.getStreamingParsing().parsingCurrencyPair;
        logger.warn("sss----{}",parsing.parsing(CurrencyPair.BTC_USDT).getParsing());
        MHAccountService accountService=bibox.getAccountService();
        BilateralPositionInfo result=accountService.getBilateralPosition(parsing.parsing(CurrencyPair.BTC_USDT));
        result.getBuyPosition();
    }

}
