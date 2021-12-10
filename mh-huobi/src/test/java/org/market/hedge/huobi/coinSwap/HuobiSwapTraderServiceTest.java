package org.market.hedge.huobi.coinSwap;

import org.junit.Test;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order;
import org.market.hedge.MHExchange;
import org.market.hedge.MHExchangeFactory;
import org.market.hedge.core.TradingArea;
import org.market.hedge.dto.trade.MHLimitOrder;
import org.market.hedge.dto.trade.MHMarketOrder;
import org.market.hedge.huobi.HuobiExchange;
import org.market.hedge.service.StreamingParsingCurrencyPair;
import org.market.hedge.service.trade.MHTradeService;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HuobiSwapTraderServiceTest {

    @Test
    public void placeLimitOrder() {
        MHExchange huobi= MHExchangeFactory.INSTANCE.createExchange(HuobiExchange.class
                ,"xxxxxxxxx"
                ,"xxxxxxxxx"
                , TradingArea.Spot);
        StreamingParsingCurrencyPair parsing=huobi.getStreamingParsing().parsingCurrencyPair;
        MHTradeService tradeService=  huobi.getTradeService();
        List<MHLimitOrder> orders=new ArrayList<>();

        CurrencyPair pair = new CurrencyPair("EKO","BTC");

        MHLimitOrder order=new MHLimitOrder(Order.OrderType.BID,
                new BigDecimal("10000") ,
                pair ,
                "112233",
                new Date(),
                new BigDecimal("0.0000000402"),
                parsing.parsing(pair)
                );

        try {
            tradeService.placeLimitOrder(order);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void cancelOrder() {
        MHExchange huobi= MHExchangeFactory.INSTANCE.createExchange(HuobiExchange.class
                ,"xxxxxxxxx"
                ,"xxxxxxxxx"
                , TradingArea.Spot);
        StreamingParsingCurrencyPair parsing=huobi.getStreamingParsing().parsingCurrencyPair;
        MHTradeService tradeService=  huobi.getTradeService();
        List<MHLimitOrder> orders=new ArrayList<>();

        CurrencyPair pair = new CurrencyPair("EKO","BTC");

        MHLimitOrder order=new MHLimitOrder(Order.OrderType.BID,
                new BigDecimal("10000") ,
                pair ,
                "112233",
                new Date(),
                new BigDecimal("0.0000000399"),
                parsing.parsing(pair)
        );

        try {
            String order_id=tradeService.placeLimitOrder(order);

            tradeService.cancelOrder(order_id);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void placeMarketOrder() {
        MHExchange huobi= MHExchangeFactory.INSTANCE.createExchange(HuobiExchange.class
                ,"xxxxxxxxx"
                ,"xxxxxxxxx"
                , TradingArea.Spot);
        StreamingParsingCurrencyPair parsing=huobi.getStreamingParsing().parsingCurrencyPair;
        MHTradeService tradeService=  huobi.getTradeService();
        List<MHLimitOrder> orders=new ArrayList<>();

        CurrencyPair pair = new CurrencyPair("EKO","BTC");

        MHMarketOrder order=new MHMarketOrder(
                Order.OrderType.BID,
                new BigDecimal("0.0001"),
                null,
                parsing.parsing(pair)
        );

        try {
            tradeService.placeMarketOrder(order);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
