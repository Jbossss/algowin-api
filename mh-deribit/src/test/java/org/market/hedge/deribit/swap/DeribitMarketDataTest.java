package org.market.hedge.deribit.swap;

import org.junit.Test;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.market.hedge.deribit.DeribitExchange;
import org.market.hedge.MHExchange;
import org.market.hedge.MHExchangeFactory;
import org.market.hedge.core.TradingArea;
import org.market.hedge.service.StreamingParsingCurrencyPair;
import org.market.hedge.service.marketdata.MHMarketDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class DeribitMarketDataTest {

    Logger logger= LoggerFactory.getLogger(DeribitMarketDataTest.class);

    @Test
    public void getDeribitOrderBookTest() throws Exception {
        MHExchange exchange = MHExchangeFactory.INSTANCE.createExchange(DeribitExchange.class, TradingArea.PerpetualSwap);
        StreamingParsingCurrencyPair parsing=exchange.getStreamingParsing().parsingCurrencyPair;
        MHMarketDataService marketDataService=exchange.getMarketDataService();
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
