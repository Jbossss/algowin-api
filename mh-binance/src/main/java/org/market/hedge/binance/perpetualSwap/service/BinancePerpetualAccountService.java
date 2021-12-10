package org.market.hedge.binance.perpetualSwap.service;

import org.knowm.xchange.client.ResilienceRegistries;
import org.market.hedge.binance.BinanceExchange;
import org.market.hedge.binance.perpetualSwap.BinancePerpetualAuthenticated;
import org.market.hedge.core.ParsingCurrencyPair;
import org.market.hedge.core.PositionInfo;
import org.market.hedge.service.account.MHAccountService;

import java.io.IOException;

public class BinancePerpetualAccountService extends BinancePerpetualAccountServiceRaw implements MHAccountService {
    public BinancePerpetualAccountService(BinanceExchange exchange, BinancePerpetualAuthenticated binance, ResilienceRegistries resilienceRegistries) {
        super(exchange, binance, resilienceRegistries);
    }

    @Override
    public PositionInfo getPosition(ParsingCurrencyPair parsingCurrencyPair, Object... args) throws IOException {
        return super.getPositionRisk(parsingCurrencyPair.getParsing(), null);
    }
}
