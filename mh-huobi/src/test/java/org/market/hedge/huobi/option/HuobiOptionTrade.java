package org.market.hedge.huobi.option;

import org.junit.Test;

import org.market.hedge.MHExchange;
import org.market.hedge.MHExchangeFactory;
import org.market.hedge.core.Direction;
import org.market.hedge.core.TradingArea;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order;

import org.market.hedge.dto.trade.MHLimitOrder;
import org.market.hedge.huobi.HuobiExchange;

import org.market.hedge.huobi.option.dto.trader.HuobiOptionTransactionHistory;
import org.market.hedge.huobi.option.service.HuobiOptionTradeService;
import org.market.hedge.service.StreamingParsingCurrencyPair;
import org.market.hedge.service.trade.MHTradeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

public class HuobiOptionTrade {

    static Logger logger= LoggerFactory.getLogger(HuobiMarketData.class);

    @Test
    public void getTransactionHistory(){
        MHExchange huobi= MHExchangeFactory.INSTANCE.createExchange(HuobiExchange.class
                ,"7c2081a2-407fa453-yh4fhmvs5k-e350d"
                ,"7fe3c480-f1d03dbe-7c396272-a5954"
                , TradingArea.Option);
        StreamingParsingCurrencyPair parsing=huobi.getStreamingParsing().parsingCurrencyPair;
        HuobiOptionTradeService tradeService= (HuobiOptionTradeService) huobi.getTradeService();
        try {
            HuobiOptionTransactionHistory[] transactionHistories=tradeService.getTransactionHistory(
                    "BTC",
                    null,
                    0,
                    1,
                    null,
                    null,
                    null);

            for (HuobiOptionTransactionHistory transactionHistory:transactionHistories){
                logger.info("{}",transactionHistory);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void placeOrder(){
        MHExchange huobi= MHExchangeFactory.INSTANCE.createExchange(HuobiExchange.class
                ,"xxxxxxxxx"
                ,"xxxxxxxxx"
                , TradingArea.Option);
        StreamingParsingCurrencyPair parsing=huobi.getStreamingParsing().parsingCurrencyPair;
        MHTradeService tradeService=huobi.getTradeService();
        try {
            tradeService.placeLimitOrder(getLimitOrder());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static MHLimitOrder getLimitOrder(){
        MHExchange huobi= MHExchangeFactory.INSTANCE.createExchange(HuobiExchange.class
                ,"xxxxxxxxx"
                ,"xxxxxxxxx"
                , TradingArea.Option);
        StreamingParsingCurrencyPair parsing=huobi.getStreamingParsing().parsingCurrencyPair;
        Order.OrderType type = Order.OrderType.EXIT_BID;
         BigDecimal originalAmount = new BigDecimal("1");
         CurrencyPair currencyPair = CurrencyPair.BTC_USDT;
         BigDecimal limitPrice = new BigDecimal("1000.00");
         Date timestamp = new Date();
         String id = "id";
         Order.OrderStatus status = Order.OrderStatus.FILLED;
         return
                new MHLimitOrder(
                        type,
                        originalAmount,
                        currencyPair,
                        id,
                        timestamp,
                        limitPrice,
                        parsing.parsing(CurrencyPair.BTC_USDT
                                ,new Date(1602835200000L)
                                ,new BigDecimal("11500")
                                , Direction.Call));
    }

    @Test
    public void cancelAll() throws IOException {

        //下多单
        MHExchange huobi= MHExchangeFactory.INSTANCE.createExchange(HuobiExchange.class
                ,"xxxxxxx"
                ,"xxxxxxxxx"
                , TradingArea.Option);
        StreamingParsingCurrencyPair parsing=huobi.getStreamingParsing().parsingCurrencyPair;
        MHTradeService tradeService=huobi.getTradeService();

       /* final Order.OrderType type = Order.OrderType.BID;
        final BigDecimal originalAmount = new BigDecimal("10");
        final CurrencyPair currencyPair = CurrencyPair.BTC_USDT;
        final BigDecimal limitPrice = new BigDecimal("100.00");
        final Date timestamp = new Date();
        final String id = "id";
        final Order.OrderStatus status = Order.OrderStatus.FILLED;
        final MHLimitOrder original =
                new MHLimitOrder(
                        type,
                        originalAmount,
                        currencyPair,
                        id,
                        timestamp,
                        limitPrice,
                        parsing.parsing(CurrencyPair.BTC_USDT
                                ,new Date(1599148800000L)
                                ,11600
                                , Direction.Call));

            List<MHLimitOrder> list=new ArrayList<>();
            list.add(original);
            list.add(getLimitOrder());
            logger.info("{}",tradeService.placeLimitOrders(list));*/


        tradeService.cancelAllByInstrument(parsing.parsing(CurrencyPair.BTC_USDT
                ,new Date(1600433211000L)
                ,new BigDecimal("10500")
                , Direction.Call));

    }

}
