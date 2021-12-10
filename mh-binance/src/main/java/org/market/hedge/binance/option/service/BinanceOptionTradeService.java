package org.market.hedge.binance.option.service;

import org.knowm.xchange.client.ResilienceRegistries;
import org.market.hedge.binance.BinanceExchange;
import org.market.hedge.binance.dto.trade.BinanceCancelledOrder;
import org.market.hedge.binance.option.BinanceOptionAuthenticated;
import org.market.hedge.binance.perpetualSwap.BinancePerpetualAuthenticated;
import org.market.hedge.core.ParsingCurrencyPair;
import org.market.hedge.dto.trade.MHLimitOrder;
import org.market.hedge.dto.trade.MHMarketOrder;
import org.market.hedge.service.trade.MHTradeService;

import java.io.IOException;
import java.util.List;

public class BinanceOptionTradeService extends BinanceOptionTradeServiceRaw implements MHTradeService {
    public BinanceOptionTradeService(BinanceExchange exchange, BinanceOptionAuthenticated binance, ResilienceRegistries resilienceRegistries) {
        super(exchange, binance, resilienceRegistries);
    }

    @Override
    public List<String> placeLimitOrders(List<MHLimitOrder> limitOrders) throws IOException {
        placeOrdersLimit(limitOrders);
        return null;
    }

    @Override
    public String placeMarketOrders(List<MHMarketOrder> marketOrder) throws IOException {
        return placeOrdersMarket(marketOrder);
    }

    @Override
    public void cancelAllByInstrument(ParsingCurrencyPair parsingCurrencyPair) throws IOException {
        cancelAllOpenOrders(parsingCurrencyPair.getParsing());
    }
}
