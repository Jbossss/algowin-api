package org.market.hedge.okex.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.market.hedge.core.ParsingCurrencyPair;
import org.market.hedge.service.marketdata.MHMarketDataService;

import java.io.IOException;


public class OkexMarketDataService  extends OkexMarketDataServiceRaw implements MHMarketDataService {

    public OkexMarketDataService(Exchange exchange) {
        super(exchange);
    }

    /**
     * @param args args[0] limit  false  Integer
     * */
    @Override
    public OrderBook getOrderBook(ParsingCurrencyPair currencyPair, Object... args) throws IOException {
        Integer depth = 10;
        if (args != null && args.length > 0) {
            if (args[0] instanceof Integer && (Integer) args[0] > 0) {
                depth = (Integer) args[0];
            }
        }
        return books(currencyPair,depth);
    }



}
