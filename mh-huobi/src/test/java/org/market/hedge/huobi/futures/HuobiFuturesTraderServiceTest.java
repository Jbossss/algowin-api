package org.market.hedge.huobi.futures;

import org.junit.Test;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order;
import org.market.hedge.MHExchange;
import org.market.hedge.MHExchangeFactory;
import org.market.hedge.core.TradingArea;
import org.market.hedge.dto.trade.MHLimitOrder;
import org.market.hedge.huobi.HuobiExchange;
import org.market.hedge.service.StreamingParsingCurrencyPair;
import org.market.hedge.service.trade.MHTradeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HuobiFuturesTraderServiceTest {

    static Logger log= LoggerFactory.getLogger(HuobiFuturesTraderServiceTest.class);

    @Test
    public void placeLimitOrders(){
        MHExchange huobi= MHExchangeFactory.INSTANCE.createExchange(HuobiExchange.class
                ,"xxxxxxxxx"
                ,"xxxxxxxxx"
                , TradingArea.Futures);
        StreamingParsingCurrencyPair parsing=huobi.getStreamingParsing().parsingCurrencyPair;
        MHTradeService tradeService=  huobi.getTradeService();
        List<MHLimitOrder> orders=new ArrayList<>();

        MHLimitOrder order1=new MHLimitOrder(Order.OrderType.ASK,
                new BigDecimal("0") ,
                CurrencyPair.BTC_USD ,
                null,
                new Date(),
                new BigDecimal("10900"),
                parsing.parsing(CurrencyPair.BTC_USD,new Date(1601625600000L)));
        order1.setLeverage("3");
        MHLimitOrder order2=new MHLimitOrder(Order.OrderType.ASK,
                new BigDecimal("-1") ,
                CurrencyPair.BTC_USD ,
                null,
                new Date(),
                new BigDecimal("10910"),
                parsing.parsing(CurrencyPair.BTC_USD,new Date(1601625600000L)));
        order1.setLeverage("3");
        order2.setLeverage("3");
        orders.add(order1);
        orders.add(order2);
        try {
            tradeService.placeLimitOrders(orders);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public  void parsing() {
        MHExchange huobi= MHExchangeFactory.INSTANCE.createExchange(HuobiExchange.class
                ,"xxxxxxxxx"
                ,"xxxxxxxxx"
                , TradingArea.Futures);
        StreamingParsingCurrencyPair parsing=huobi.getStreamingParsing().parsingCurrencyPair;
        MHTradeService tradeService=  huobi.getTradeService();
        List<MHLimitOrder> orders=new ArrayList<>();

        log.info(parsing.parsing(CurrencyPair.BTC_USD,new Date(1601625600000L)).getParsing());
    }

}
