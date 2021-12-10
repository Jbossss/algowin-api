package org.market.hedge.binance.option.service;

import org.knowm.xchange.client.ResilienceRegistries;
import org.market.hedge.binance.BinanceExchange;
import org.market.hedge.binance.option.BinanceOptionAuthenticated;
import org.market.hedge.binance.perpetualSwap.BinancePerpetualAuthenticated;

public class BinanceOptionAccountServiceRaw extends BinanceOptionBaseService {
    protected BinanceOptionAccountServiceRaw(BinanceExchange exchange, BinanceOptionAuthenticated binance, ResilienceRegistries resilienceRegistries) {
        super(exchange, binance, resilienceRegistries);
    }
}
