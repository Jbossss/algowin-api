package org.market.hedge.deribit.option;

import org.junit.Test;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.market.hedge.MHExchange;
import org.market.hedge.MHExchangeFactory;
import org.market.hedge.core.Direction;
import org.market.hedge.core.ParsingCurrencyPair;
import org.market.hedge.core.TradingArea;
import org.market.hedge.deribit.DeribitExchange;
import org.market.hedge.dto.trade.MHLimitOrder;
import org.market.hedge.service.StreamingParsingCurrencyPair;
import org.market.hedge.service.marketdata.MHMarketDataService;
import org.market.hedge.service.trade.MHTradeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DeribitOptionTradeDataTest {


    static Logger logger= LoggerFactory.getLogger(DeribitOptionTradeDataTest.class);


    @Test
    public void placeLimitOrder() {
        //MHExchange exchange = MHExchangeFactory.INSTANCE.createExchange(DeribitExchange.class,"xxxxxx","xxxxxx", TradingArea.Option);
        MHExchange exchange = MHExchangeFactory.INSTANCE.createExchange(DeribitExchange.class,"xxxxxx","xxxxxx", TradingArea.Option);

        StreamingParsingCurrencyPair parsing=exchange.getStreamingParsing().parsingCurrencyPair;
        MHTradeService tradeService=  exchange.getTradeService();
        ParsingCurrencyPair pair=parsing.parsing(CurrencyPair.BTC_USDT,new Date(1613116800000L),new BigDecimal("36000"), Direction.Call);
        try {
            MHLimitOrder order1=
                    new MHLimitOrder(
                            Order.OrderType.EXIT_ASK,
                            new BigDecimal("0.1") ,
                            CurrencyPair.BTC_USDT ,
                            "11223311",
                            new Date(),
                            new BigDecimal("0.2"),
                            pair);
            logger.info(pair.getParsing());
            List<MHLimitOrder> limitOrders=new ArrayList<>();
            limitOrders.add(order1);
            tradeService.placeLimitOrders(limitOrders);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
