package org.market.hedge.bibox.usdtswap.service;

import org.knowm.xchange.Exchange;
import org.market.hedge.core.ParsingCurrencyPair;
import org.market.hedge.dto.trade.MHLimitOrder;
import org.market.hedge.service.trade.MHTradeService;

import java.io.IOException;

public class BiboxUSDTSwapTradeService extends BiboxUSDTSwapTradeServiceRaw implements MHTradeService {
    /**
     * Constructor
     *
     * @param exchange
     */
    public BiboxUSDTSwapTradeService(Exchange exchange) {
        super(exchange);
    }

    @Override
    public String placeLimitOrder(MHLimitOrder limitOrder) throws IOException {
        return placeBiboxLimitOrder(limitOrder).toString();
    }

    @Override
    public void cancelAllByInstrument(ParsingCurrencyPair parsingCurrencyPair) throws IOException {
        cancelAllBibox(parsingCurrencyPair);
    }

}
