package org.market.hedge.huobi.usdtSwap;

import org.junit.Test;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.market.hedge.MHExchange;
import org.market.hedge.MHExchangeFactory;
import org.market.hedge.core.TradingArea;
import org.market.hedge.huobi.HuobiExchange;
import org.market.hedge.service.StreamingParsingCurrencyPair;
import org.market.hedge.service.marketdata.MHMarketDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class HuobiUsdtSwapMarketData {

    static Logger logger= LoggerFactory.getLogger(HuobiUsdtSwapMarketData.class);

    @Test
    public void getOrderBook() {
        MHExchange huobi= MHExchangeFactory.INSTANCE.createExchange(HuobiExchange.class, TradingArea.PerpetualSwap);
        StreamingParsingCurrencyPair parsing=huobi.getStreamingParsing().parsingCurrencyPair;
        MHMarketDataService marketDataService=huobi.getMarketDataService();
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
