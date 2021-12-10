package org.market.hedge.deribit.perpetualSwap.service;

import org.market.hedge.deribit.DeribitExchange;
import org.market.hedge.deribit.dto.Kind;
import org.market.hedge.deribit.dto.marketdata.DeribitCurrency;
import org.market.hedge.deribit.dto.marketdata.DeribitInstrument;
import org.market.hedge.deribit.dto.marketdata.DeribitOrderBook;
import org.market.hedge.deribit.service.DeribitBaseService;

import java.io.IOException;
import java.util.List;

public class DeribitPerpetualSwapMarketDataServiceRaw extends DeribitBaseService {

    /**
     * Constructor
     *
     * @param exchange DeribitExchange
     */
    public DeribitPerpetualSwapMarketDataServiceRaw(DeribitExchange exchange) {
        super(exchange);
    }

    public List<DeribitInstrument> getDeribitInstruments(String currency, Kind kind, Boolean expired)
            throws IOException {
        return deribit.getInstruments(currency, kind, expired).getResult();
    }

    public List<DeribitCurrency> getDeribitCurrencies() throws IOException {
        return deribit.getCurrencies().getResult();
    }

    public DeribitOrderBook getDeribitOrderBook(String instrumentName, Integer depth)
            throws IOException {
        return deribit.getOrderBook(instrumentName, depth).getResult();
    }

}
