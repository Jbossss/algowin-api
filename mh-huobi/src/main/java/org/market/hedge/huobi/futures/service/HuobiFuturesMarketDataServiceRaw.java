package org.market.hedge.huobi.futures.service;


import org.knowm.xchange.Exchange;
import org.market.hedge.core.ParsingCurrencyPair;
import org.market.hedge.huobi.dto.marketdata.HuobiDepth;
import org.market.hedge.huobi.dto.marketdata.results.HuobiDepthResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class HuobiFuturesMarketDataServiceRaw extends HuobiFuturesBaseService {

    private static final Logger LOGGER= LoggerFactory.getLogger(HuobiFuturesMarketDataService.class);

    protected HuobiFuturesMarketDataServiceRaw(Exchange exchange) {
        super(exchange);
    }

    public HuobiDepth getHuobiDepth(ParsingCurrencyPair parsingCurrencyPair, String depthType) throws IOException {
        HuobiDepthResult depthResult = huobiFutures.getDepth(parsingCurrencyPair.getParsing(), depthType);
        return checkResult(depthResult);
    }
}
