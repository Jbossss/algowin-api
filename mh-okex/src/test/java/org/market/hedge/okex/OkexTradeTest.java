package org.market.hedge.okex;

import com.okex.open.api.bean.trade.param.PlaceOrder;
import org.junit.Test;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order;
import org.market.hedge.MHExchange;
import org.market.hedge.MHExchangeFactory;
import org.market.hedge.MHExchangeSpecification;
import org.market.hedge.core.Direction;
import org.market.hedge.core.TradingArea;
import org.market.hedge.dto.trade.MHLimitOrder;
import org.market.hedge.service.StreamingParsingCurrencyPair;
import org.market.hedge.service.trade.MHTradeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OkexTradeTest {

    static Logger logger= LoggerFactory.getLogger(OkexTradeTest.class);


/**
     * 批量下单 Place Multiple Orders
     * POST /api/v5/trade/batch-orders
     */

    @Test
    public void placeMultipleOrders(){

        List<PlaceOrder> placeOrders =new ArrayList<>();

        PlaceOrder placeOrder1 =new PlaceOrder();
        placeOrder1.setInstId("BTC-USD-211008-40000-C");
        placeOrder1.setTdMode("cross");
        //placeOrder1.setClOrdId("12);
        //placeOrder1.setTag("");
        placeOrder1.setSide("buy");
        placeOrder1.setPosSide("");
        placeOrder1.setOrdType("limit");
        placeOrder1.setSz("1");
        placeOrder1.setPx("0.002");
        placeOrder1.setReduceOnly(null);
        placeOrders.add(placeOrder1);

        //JSONObject result = tradeAPIService.placeMultipleOrders(placeOrders);

        //toResultString(LOG, "result", result);
    }


/**
     * 批量撤单 Cancel Multiple Orders
     * POST /api/v5/trade/cancel-batch-orders
     */

    @Test
    public void cancelMultipleOrders(){
        MHExchangeSpecification exSpec = new OkexExchange().getDefaultExchangeSpecification(TradingArea.Option);
        exSpec.setUserName("pwn2own");
        exSpec.setApiKey("xxxxxxx");
        exSpec.setSecretKey("xxxxxxx");
        MHExchange okex = MHExchangeFactory.INSTANCE.createExchange(exSpec);
        StreamingParsingCurrencyPair parsing=okex.getStreamingParsing().parsingCurrencyPair;
        MHTradeService tradeService=okex.getTradeService();
        try {
            tradeService.cancelAllByInstrument(parsing.parsing(CurrencyPair.BTC_USD
                    ,new Date(1636099200000L)
                    ,new BigDecimal("61000")
                    , Direction.Put));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void placeOrder() {
        MHExchangeSpecification exSpec = new OkexExchange().getDefaultExchangeSpecification(TradingArea.Option);
        exSpec.setUserName("pwn2own");
        exSpec.setApiKey("xxxxxxx");
        exSpec.setSecretKey("xxxxxxx");
        MHExchange okex = MHExchangeFactory.INSTANCE.createExchange(exSpec);
        StreamingParsingCurrencyPair parsing=okex.getStreamingParsing().parsingCurrencyPair;
        MHTradeService tradeService=okex.getTradeService();
        List<MHLimitOrder> orders=new ArrayList<>();
        MHLimitOrder order1=new MHLimitOrder(Order.OrderType.BID,
                new BigDecimal("1") ,
                CurrencyPair.BTC_USD ,
                null,
                new Date(),
                new BigDecimal("0.0002"),
                parsing.parsing(CurrencyPair.BTC_USD
                        ,new Date(1636099200000L)
                        ,new BigDecimal("61000")
                        , Direction.Put));
        MHLimitOrder order2=new MHLimitOrder(Order.OrderType.BID,
                new BigDecimal("1") ,
                CurrencyPair.BTC_USD ,
                null,
                new Date(),
                new BigDecimal("0.0001"),
                parsing.parsing(CurrencyPair.BTC_USD
                        ,new Date(1636099200000L)
                        ,new BigDecimal("61000")
                        , Direction.Put));
        orders.add(order1);
        orders.add(order2);
        try {
            List<String> result=tradeService.placeLimitOrders(orders);
            for (String s : result) {
                logger.info("-----------------");
                logger.info(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

