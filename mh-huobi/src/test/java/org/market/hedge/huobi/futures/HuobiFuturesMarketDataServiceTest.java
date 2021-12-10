package org.market.hedge.huobi.futures;

import org.junit.Test;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.market.hedge.MHExchange;
import org.market.hedge.MHExchangeFactory;
import org.market.hedge.core.TradingArea;
import org.market.hedge.huobi.HuobiExchange;
import org.market.hedge.huobi.futures.dto.marketdata.HuobiFuturesContractInfo;
import org.market.hedge.huobi.futures.service.HuobiFuturesMarketDataService;
import org.market.hedge.huobi.coinSwap.HuobiSwapMarketData;
import org.market.hedge.service.StreamingParsingCurrencyPair;
import org.market.hedge.service.marketdata.MHMarketDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class HuobiFuturesMarketDataServiceTest {

    static Logger logger= LoggerFactory.getLogger(HuobiSwapMarketData.class);

    @Test
    public void getOrderBoook(){
        MHExchange huobi= MHExchangeFactory.INSTANCE.createExchange(HuobiExchange.class, TradingArea.Futures);
        StreamingParsingCurrencyPair parsing=huobi.getStreamingParsing().parsingCurrencyPair;
        MHMarketDataService marketDataService=huobi.getMarketDataService();
        try {
            for (int i=0;i<100;i++){
                OrderBook orderBook=marketDataService.getOrderBook(parsing.parsing(CurrencyPair.BTC_USD,new Date(1603440000000L)));
                logger.info("{}","ask:"+orderBook.getAsks().get(0).getLimitPrice()+" bid:"+orderBook.getBids().get(0).getLimitPrice());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getContractInfos(){
        MHExchange huobi= MHExchangeFactory.INSTANCE.createExchange(HuobiExchange.class, TradingArea.Futures);
        StreamingParsingCurrencyPair parsing=huobi.getStreamingParsing().parsingCurrencyPair;
        HuobiFuturesMarketDataService marketDataService= (HuobiFuturesMarketDataService) huobi.getMarketDataService();
        try {

            List<HuobiFuturesContractInfo> result=marketDataService.getContractInfos(CurrencyPair.BTC_USDT);
            result.forEach(e->{
                logger.info("getContract_code: {}  getCreate_date: {}  getDelivery_date: {} ",e.getContract_code(),e.getCreate_date(),e.getDelivery_date());
            });

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        SimpleDateFormat sd=new SimpleDateFormat("yyyyMMdd");
        try {
            Date date=sd.parse("20201204");
            logger.info("{}",date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
