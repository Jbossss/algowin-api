package org.market.hedge;


import org.knowm.xchange.Exchange;
import org.market.hedge.core.StreamingParsing;
import org.market.hedge.core.TradingArea;
import org.market.hedge.service.account.MHAccountService;
import org.market.hedge.service.marketdata.MHMarketDataService;
import org.market.hedge.service.trade.MHTradeService;

public interface MHExchange extends Exchange {

    MHExchangeSpecification getDefaultExchangeSpecification(TradingArea tradingArea);

    MHMarketDataService getMarketDataService();

    MHTradeService getTradeService();

    MHAccountService getAccountService();


    StreamingParsing getStreamingParsing();


    void applySpecification(MHExchangeSpecification var1);
}
