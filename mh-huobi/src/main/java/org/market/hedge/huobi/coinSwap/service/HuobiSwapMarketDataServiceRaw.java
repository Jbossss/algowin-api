package org.market.hedge.huobi.coinSwap.service;


import org.knowm.xchange.Exchange;
import org.market.hedge.core.ParsingCurrencyPair;
import org.market.hedge.huobi.dto.marketdata.HuobiDepth;
import org.market.hedge.huobi.dto.marketdata.results.HuobiDepthResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.IOException;

public class HuobiSwapMarketDataServiceRaw extends HuobiSwapBaseService {

    private static final Logger LOGGER= LoggerFactory.getLogger(HuobiSwapMarketDataService.class);

    protected HuobiSwapMarketDataServiceRaw(Exchange exchange) {
        super(exchange);
    }

    public HuobiDepth getHuobiDepth(ParsingCurrencyPair parsingCurrencyPair, String depthType) throws IOException {
        HuobiDepthResult depthResult = huobiSwap.getDepth(parsingCurrencyPair.getParsing(), depthType);
        return checkResult(depthResult);
    }
}
