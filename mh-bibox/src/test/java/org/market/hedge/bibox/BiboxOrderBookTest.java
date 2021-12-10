package org.market.hedge.bibox;

import org.knowm.xchange.ExchangeSpecification;
import org.market.hedge.MHExchange;
import org.market.hedge.MHExchangeFactory;
import org.market.hedge.MHExchangeSpecification;
import org.market.hedge.core.TradingArea;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;


public class BiboxOrderBookTest {

    private static final Logger log= LoggerFactory.getLogger(BiboxOrderBookTest.class);

    public static void main(String[] args) throws IOException {
        MHExchange BIBOX =
                MHExchangeFactory.INSTANCE.createExchange(BiboxExchange.class.getName(), TradingArea.Spot);
        /*MarketDataService marketDataService = BIBOX.getMarketDataService();
        OrderBook orderBook=marketDataService.getOrderBook(CurrencyPair.BTC_USDT);
        log.info("{}",orderBook.getAsks());
        log.info("{}",orderBook.getBids());*/
        ExchangeSpecification exchangeSpecification=BIBOX.getExchangeSpecification();
        BIBOX.getTradeService().placeLimitOrder(new LimitOrder(Order.OrderType.BID,
                new BigDecimal("1"),
                CurrencyPair.BTC_USDT,
                "112233",
                new Date(),
                new BigDecimal(11000)));

        MHExchangeSpecification exSpec = new BiboxExchange().getDefaultExchangeSpecification(TradingArea.Spot);
        exSpec.setUserName("34387");
        exSpec.setApiKey("xxxxxxx");
        exSpec.setSecretKey("xxxxxxx");
        MHExchange bitstamp = MHExchangeFactory.INSTANCE.createExchange(exSpec);
    }

}
