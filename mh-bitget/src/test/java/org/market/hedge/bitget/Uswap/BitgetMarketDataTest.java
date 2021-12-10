package org.market.hedge.bitget.Uswap;

import org.junit.Test;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.market.hedge.MHExchange;
import org.market.hedge.MHExchangeFactory;
import org.market.hedge.bitget.BitgetExchange;
import org.market.hedge.core.TradingArea;
import org.market.hedge.service.StreamingParsingCurrencyPair;
import org.market.hedge.service.marketdata.MHMarketDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BitgetMarketDataTest {

    Logger logger= LoggerFactory.getLogger(BitgetMarketDataTest.class);

    public org.market.hedge.bitget.usdtswap.service.BitgetUSwapMarketDataService BitgetUSwapMarketDataService;

    @Test
    public void getBitgetOrderBookTest()  {
        MHExchange exchange = MHExchangeFactory.INSTANCE.createExchange(BitgetExchange.class, TradingArea.PerpetualSwap);
        StreamingParsingCurrencyPair parsing=exchange.getStreamingParsing().parsingCurrencyPair;
        MHMarketDataService marketDataService=exchange.getMarketDataService();

        try {
            for (int i=0;i<10;i++){
                OrderBook orderBook=marketDataService.getOrderBook(parsing.parsing(CurrencyPair.BTC_USDT),5);
                logger.info("{}","ask0:"+orderBook.getAsks().get(0).getLimitPrice()+" bid0:"+orderBook.getBids().get(0).getLimitPrice());
                logger.info("{}","ask1:"+orderBook.getAsks().get(1).getLimitPrice()+" bid1:"+orderBook.getBids().get(1).getLimitPrice());
                logger.info("{}","ask2:"+orderBook.getAsks().get(2).getLimitPrice()+" bid2:"+orderBook.getBids().get(2).getLimitPrice());
                logger.info("{}","ask3:"+orderBook.getAsks().get(3).getLimitPrice()+" bid3:"+orderBook.getBids().get(3).getLimitPrice());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }





}
