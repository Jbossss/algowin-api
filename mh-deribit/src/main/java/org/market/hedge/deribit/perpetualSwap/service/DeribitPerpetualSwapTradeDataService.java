package org.market.hedge.deribit.perpetualSwap.service;

import org.market.hedge.deribit.DeribitExchange;
import org.market.hedge.deribit.service.DeribitTradeServiceRaw;
import org.market.hedge.dto.trade.MHLimitOrder;
import org.market.hedge.service.trade.MHTradeService;

import java.io.IOException;

public class DeribitPerpetualSwapTradeDataService extends DeribitTradeServiceRaw implements MHTradeService {

    public DeribitPerpetualSwapTradeDataService(DeribitExchange exchange) {
        super(exchange);
    }

    @Override
    public String placeLimitOrder(MHLimitOrder limitOrder) throws IOException {
        return null;
    }
}
