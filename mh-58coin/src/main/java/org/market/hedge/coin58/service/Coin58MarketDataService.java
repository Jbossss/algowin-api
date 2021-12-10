package org.market.hedge.coin58.service;

import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.service.marketdata.MarketDataService;

import java.io.IOException;

public class Coin58MarketDataService implements MarketDataService {

    public OrderBook getOrderBook(CurrencyPair currencyPair, Object... args) throws IOException {
        return null;
    }
}
