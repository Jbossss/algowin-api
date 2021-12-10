package org.market.hedge.binance.option.service;

import org.knowm.xchange.client.ResilienceRegistries;
import org.market.hedge.binance.BinanceExchange;
import org.market.hedge.binance.option.BinanceOptionAuthenticated;
import org.market.hedge.binance.perpetualSwap.BinancePerpetualAuthenticated;
import org.market.hedge.service.account.MHAccountService;

public class BinanceOptionAccountService extends BinanceOptionAccountServiceRaw implements MHAccountService {
    public BinanceOptionAccountService(BinanceExchange exchange, BinanceOptionAuthenticated binance, ResilienceRegistries resilienceRegistries) {
        super(exchange, binance, resilienceRegistries);
    }
}
