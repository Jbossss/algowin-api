package org.market.hedge.deribit.perpetualSwap.service;

import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.exceptions.ExchangeException;
import org.market.hedge.core.ParsingCurrencyPair;
import org.market.hedge.deribit.DeribitAdapters;
import org.market.hedge.deribit.DeribitExchange;
import org.market.hedge.deribit.dto.DeribitException;
import org.market.hedge.deribit.dto.Kind;
import org.market.hedge.deribit.dto.marketdata.DeribitInstrument;
import org.market.hedge.deribit.dto.marketdata.DeribitOrderBook;
import org.market.hedge.service.marketdata.MHMarketDataService;

import java.io.IOException;
import java.util.List;

public class DeribitPerpetualSwapMarketDataService extends DeribitPerpetualSwapMarketDataServiceRaw implements MHMarketDataService {
    /**
     * Constructor
     *
     * @param exchange DeribitExchange
     */
    public DeribitPerpetualSwapMarketDataService(DeribitExchange exchange) {
        super(exchange);
    }

    @Override
    public OrderBook getOrderBook(ParsingCurrencyPair currencyPair, Object... args) throws IOException {
        String deribitInstrumentName = DeribitAdapters.adaptInstrumentName(currencyPair.getCurrencyPair(), new String[]{"PERPETUAL"});
        DeribitOrderBook deribitOrderBook;
        try {
            deribitOrderBook = super.getDeribitOrderBook(deribitInstrumentName, null);
        } catch (DeribitException ex) {
            throw new ExchangeException(ex);
        }

        return DeribitAdapters.adaptOrderBook(deribitOrderBook);
    }

    public List<DeribitInstrument> getContractInfos(String currency, Kind kind, Boolean expired)
            throws IOException {
        return getDeribitInstruments(currency, kind, expired);
    }


    public DeribitOrderBook getDeribitOrderBook(CurrencyPair currencyPair, Object... args) throws IOException {
        String deribitInstrumentName = DeribitAdapters.adaptInstrumentName(currencyPair, args);
        DeribitOrderBook deribitOrderBook;
        try {
            deribitOrderBook = getDeribitOrderBook(deribitInstrumentName, null);
        } catch (DeribitException ex) {
            throw new ExchangeException(ex);
        }

        return deribitOrderBook;
    }
}
