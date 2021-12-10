package org.market.hedge.binance.perpetualSwap;

import org.junit.Test;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.market.hedge.MHExchange;
import org.market.hedge.MHExchangeFactory;

import org.market.hedge.binance.BinanceExchange;
import org.market.hedge.core.Kline;
import org.market.hedge.core.KlineInterval;
import org.market.hedge.core.PremiumIndex;
import org.market.hedge.core.TradingArea;
import org.market.hedge.service.StreamingParsingCurrencyPair;
import org.market.hedge.service.marketdata.MHMarketDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class BinancePerpetualMarketDataServiceTest {

    Logger logger= LoggerFactory.getLogger(BinancePerpetualMarketDataServiceTest.class);


    @Test
    public void klines() throws IOException {
        MHExchange exchange = MHExchangeFactory.INSTANCE.createExchange(BinanceExchange.class, TradingArea.PerpetualSwap);
        StreamingParsingCurrencyPair parsing=exchange.getStreamingParsing().parsingCurrencyPair;
        MHMarketDataService marketDataService=exchange.getMarketDataService();
        try {
            List<Kline> getKlines=marketDataService.getKlines(parsing.parsing(CurrencyPair.BTC_USDT), KlineInterval.m15,20);
            getKlines.forEach(e->{
                logger.info("{}",e.getClose());
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Test
    public void  getOrderBook() throws IOException {
        MHExchange exchange = MHExchangeFactory.INSTANCE.createExchange(BinanceExchange.class, TradingArea.PerpetualSwap);
        StreamingParsingCurrencyPair parsing=exchange.getStreamingParsing().parsingCurrencyPair;
        MHMarketDataService marketDataService=exchange.getMarketDataService();
        try {
            OrderBook orderBook =marketDataService.getOrderBook(parsing.parsing(CurrencyPair.BTC_USDT),20);
            for (int i=0;i<10;i++){
                logger.info("{}","ask:"+orderBook.getAsks().get(i).getLimitPrice()+" bid:"+orderBook.getBids().get(i).getLimitPrice());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void  getAllPremiumIndex() throws IOException {
        MHExchange exchange = MHExchangeFactory.INSTANCE.createExchange(BinanceExchange.class, TradingArea.PerpetualSwap);
        StreamingParsingCurrencyPair parsing=exchange.getStreamingParsing().parsingCurrencyPair;
        MHMarketDataService marketDataService=exchange.getMarketDataService();
        try {
            List<PremiumIndex>  result =marketDataService.getAllPremiumIndex();
            result.forEach(e->{
                logger.info(e.getSymbol()+"費率："+e.getLastFundingRate());
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void  getPremiumIndex() throws IOException {
        MHExchange exchange = MHExchangeFactory.INSTANCE.createExchange(BinanceExchange.class, TradingArea.PerpetualSwap);
        StreamingParsingCurrencyPair parsing=exchange.getStreamingParsing().parsingCurrencyPair;
        MHMarketDataService marketDataService=exchange.getMarketDataService();
        /*try {
            List<PremiumIndex>  result =marketDataService.getPremiumIndex(parsing.);
            for (int i=0;i<10;i++){
                logger.info("getLastFundingRate:{}",result.get(0).getLastFundingRate());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/

    }



}
