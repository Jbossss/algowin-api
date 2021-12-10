package org.market.hedge.binance.option;

import org.junit.Test;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order;
import org.market.hedge.MHExchange;
import org.market.hedge.MHExchangeFactory;
import org.market.hedge.binance.BinanceExchange;
import org.market.hedge.binance.perpetualSwap.BinancePerpetualMarketDataServiceTest;
import org.market.hedge.core.Direction;
import org.market.hedge.core.ParsingCurrencyPair;
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


public class BinanceOptionTradeServiceTest {

    Logger log= LoggerFactory.getLogger(BinancePerpetualMarketDataServiceTest.class);

    @Test
    public void placeLimitOrder() {
        MHExchange exchange= MHExchangeFactory.INSTANCE.createExchange(BinanceExchange.class
                ,"xxxxxx"
                ,"xxxxxx"
                , TradingArea.Option);
        StreamingParsingCurrencyPair parsing=exchange.getStreamingParsing().parsingCurrencyPair;
        MHTradeService tradeService=  exchange.getTradeService();
        ParsingCurrencyPair pair=parsing.parsing(CurrencyPair.BTC_USDT
                ,new Date(1623945600000L)
                ,new BigDecimal("25000")
                , Direction.Put);
        try {
            MHLimitOrder order1=
                    new MHLimitOrder(
                            Order.OrderType.BID,
                            new BigDecimal("1") ,
                            CurrencyPair.BTC_USDT ,
                            "11223311",
                            new Date(),
                            new BigDecimal("0.5"),
                            pair);
            log.info(pair.getParsing());
            List<MHLimitOrder> limitOrders=new ArrayList<>();
            limitOrders.add(order1);
            tradeService.placeLimitOrders(limitOrders);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void cancelAllByInstrument(){
        MHExchange exchange= MHExchangeFactory.INSTANCE.createExchange(BinanceExchange.class
                ,"xxxxxx"
                ,"xxxxxx"
                , TradingArea.Option);
        StreamingParsingCurrencyPair parsing=exchange.getStreamingParsing().parsingCurrencyPair;
        MHTradeService tradeService=  exchange.getTradeService();
        ParsingCurrencyPair pair=parsing.parsing(CurrencyPair.BTC_USDT
                ,new Date(1623945600000L)
                ,new BigDecimal("25000")
                , Direction.Put);
        log.info(pair.getParsing());
        try {
            tradeService.cancelAllByInstrument(pair);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

}
