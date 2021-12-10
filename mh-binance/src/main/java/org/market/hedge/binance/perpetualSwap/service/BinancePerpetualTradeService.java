package org.market.hedge.binance.perpetualSwap.service;

import org.knowm.xchange.client.ResilienceRegistries;
import org.market.hedge.binance.BinanceExchange;
import org.market.hedge.binance.perpetualSwap.BinancePerpetualAuthenticated;
import org.market.hedge.binance.perpetualSwap.dto.trade.req.BinancePerpetualLeverage;
import org.market.hedge.core.ParsingCurrencyPair;
import org.market.hedge.dto.trade.MHLimitOrder;
import org.market.hedge.dto.trade.MHMarketOrder;
import org.market.hedge.dto.trade.Result;
import org.market.hedge.service.trade.MHTradeService;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class BinancePerpetualTradeService extends BinancePerpetualTradeServiceRaw implements MHTradeService {
    public BinancePerpetualTradeService(BinanceExchange exchange, BinancePerpetualAuthenticated binance, ResilienceRegistries resilienceRegistries) {
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
    public boolean getPositionSideDual(Long recvWindow) throws IOException {
        return super.getPositionSideDual(recvWindow);
    }

    @Override
    public HashMap setPositionSideDual(String dualSidePosition, Long recvWindow) throws IOException {
        return setPositionSideDual(dualSidePosition, recvWindow);
    }

    @Override
    public void setLeverage(ParsingCurrencyPair parsingCurrencyPair, Integer leverage) throws IOException {
        BinancePerpetualLeverage result=setLeverageRaw(parsingCurrencyPair,leverage);
    }
}
