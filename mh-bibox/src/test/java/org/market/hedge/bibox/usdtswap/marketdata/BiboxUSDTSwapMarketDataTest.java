package org.market.hedge.bibox.usdtswap.marketdata;

import org.junit.Test;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.market.hedge.MHExchange;
import org.market.hedge.MHExchangeFactory;
import org.market.hedge.bibox.BiboxExchange;
import org.market.hedge.core.TradingArea;
import org.market.hedge.service.StreamingParsingCurrencyPair;
import org.market.hedge.service.marketdata.MHMarketDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class BiboxUSDTSwapMarketDataTest {

    private static Logger logger= LoggerFactory.getLogger(BiboxUSDTSwapMarketDataTest.class);

    @Test
    public void getOrderBook(){
        MHExchange bibox= MHExchangeFactory.INSTANCE.createExchange(BiboxExchange.class, TradingArea.PerpetualSwap);
        StreamingParsingCurrencyPair parsing=bibox.getStreamingParsing().parsingCurrencyPair;
        logger.warn("sssss----{}",parsing.parsing(CurrencyPair.BTC_USD).getParsing());
        MHMarketDataService marketDataService=bibox.getMarketDataService();
        try {
            for (int i=0;i<10;i++){
                OrderBook orderBook=marketDataService.getOrderBook(parsing.parsing(CurrencyPair.BTC_USDT));
                logger.info("{}","ask:"+orderBook.getAsks().get(0).getLimitPrice()+" bid:"+orderBook.getBids().get(0).getLimitPrice());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
