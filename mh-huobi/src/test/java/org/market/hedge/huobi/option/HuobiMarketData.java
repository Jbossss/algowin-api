package org.market.hedge.huobi.option;

import org.junit.Test;
import org.market.hedge.MHExchange;
import org.market.hedge.MHExchangeFactory;
import org.market.hedge.core.Direction;
import org.market.hedge.core.TradingArea;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.market.hedge.huobi.HuobiExchange;
import org.market.hedge.huobi.option.dto.marketdata.HuobiOptionContractInfo;
import org.market.hedge.huobi.option.dto.marketdata.HuobiOptionContractType;
import org.market.hedge.huobi.option.service.HuobiOptionMarketDataService;
import org.market.hedge.service.StreamingParsingCurrencyPair;
import org.market.hedge.service.marketdata.MHMarketDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Date;

public class HuobiMarketData {

    static Logger logger= LoggerFactory.getLogger(HuobiMarketData.class);

    @Test
    public void OptionContractInfo(){
        MHExchange huobi= MHExchangeFactory.INSTANCE.createExchange(HuobiExchange.class, TradingArea.Option);
        StreamingParsingCurrencyPair parsing=huobi.getStreamingParsing().parsingCurrencyPair;
        HuobiOptionMarketDataService marketDataService= (HuobiOptionMarketDataService) huobi.getMarketDataService();
        try {
            HuobiOptionContractInfo[] contractInfos=marketDataService.getOptionContractInfo("BTC",null, HuobiOptionContractType.quarter,null);
            for (HuobiOptionContractInfo contractInfo : contractInfos) {
                logger.info("{}", contractInfo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getOrderBook() {
        MHExchange huobi= MHExchangeFactory.INSTANCE.createExchange(HuobiExchange.class, TradingArea.Option);
        StreamingParsingCurrencyPair parsing=huobi.getStreamingParsing().parsingCurrencyPair;
        MHMarketDataService marketDataService=huobi.getMarketDataService();
        try {
            for (int i=0;i<10;i++){
                OrderBook orderBook=marketDataService.getOrderBook(parsing.parsing(CurrencyPair.BTC_USDT
                        ,new Date(1600401600000L)
                        ,10500
                        ,Direction.Call));
                logger.info("{}","ask:"+orderBook.getAsks().get(0).getLimitPrice()+" bid:"+orderBook.getBids().get(0).getLimitPrice());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
