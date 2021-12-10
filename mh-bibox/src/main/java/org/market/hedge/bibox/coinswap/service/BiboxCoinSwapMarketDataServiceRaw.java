package org.market.hedge.bibox.coinswap.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.exceptions.ExchangeException;
import org.market.hedge.bibox.BiboxException;
import org.market.hedge.bibox.dto.BiboxResponse;
import org.market.hedge.bibox.dto.trade.BiboxOrderBook;
import org.market.hedge.core.ParsingCurrencyPair;

import java.io.IOException;

public class BiboxCoinSwapMarketDataServiceRaw extends BiboxCoinSwapBaseService{

    private static final String TICKER_CMD = "ticker";
    private static final String DEPTH_CMD = "depth";
    private static final String ALL_TICKERS_CMD = "marketAll";
    private static final String DEALS_CMD = "deals";

    /**
     * Constructor
     *
     * @param exchange
     */
    protected BiboxCoinSwapMarketDataServiceRaw(Exchange exchange) {
        super(exchange);
    }


    public BiboxOrderBook getBiboxOrderBook(ParsingCurrencyPair currencyPair, Integer depth)
            throws IOException {
        try {
            BiboxResponse<BiboxOrderBook> response =
                    bibox.orderBook(currencyPair.getParsing(), depth);
            throwErrors(response);
            return response.getResult();
        } catch (BiboxException e) {
            throw new ExchangeException(e.getMessage());
        }
    }
}
