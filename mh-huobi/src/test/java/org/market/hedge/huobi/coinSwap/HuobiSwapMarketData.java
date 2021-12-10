package org.market.hedge.huobi.coinSwap;

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

public class HuobiSwapMarketData {

    static Logger logger= LoggerFactory.getLogger(HuobiSwapMarketData.class);

    public void getOrderBook() {
        MHExchange huobi= MHExchangeFactory.INSTANCE.createExchange(HuobiExchange.class, TradingArea.Spot);
        StreamingParsingCurrencyPair parsing=huobi.getStreamingParsing().parsingCurrencyPair;
        MHMarketDataService marketDataService=huobi.getMarketDataService();
        try {
            for (int i=0;i<10;i++){
                OrderBook orderBook=marketDataService.getOrderBook(parsing.parsing(new CurrencyPair("EKO","BTC")));
                logger.info("{}","ask:"+orderBook.getAsks().get(0).getLimitPrice().toPlainString()+" bid:"+orderBook.getBids().get(0).getLimitPrice().toPlainString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getUSDTSwapOrderBook() {
        MHExchange huobi= MHExchangeFactory.INSTANCE.createExchange(HuobiExchange.class, TradingArea.CoinSwap);
        StreamingParsingCurrencyPair parsing=huobi.getStreamingParsing().parsingCurrencyPair;
        MHMarketDataService marketDataService=huobi.getMarketDataService();
        try {
            for (int i=0;i<10;i++){
                OrderBook orderBook=marketDataService.getOrderBook(parsing.parsing(CurrencyPair.BTC_USD));
                logger.info("{}","ask:"+orderBook.getAsks().get(0).getLimitPrice().toPlainString()+" bid:"+orderBook.getBids().get(0).getLimitPrice().toPlainString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
