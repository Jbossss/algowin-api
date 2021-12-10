package org.market.hedge.binance.option;

import org.junit.Test;
import org.market.hedge.MHExchange;
import org.market.hedge.MHExchangeFactory;
import org.market.hedge.binance.BinanceExchange;
import org.market.hedge.binance.option.dto.marketdat.resq.OptionInfo;
import org.market.hedge.binance.option.service.BinanceOptionMarketDataService;
import org.market.hedge.binance.perpetualSwap.BinancePerpetualMarketDataServiceTest;
import org.market.hedge.core.TradingArea;
import org.market.hedge.service.StreamingParsingCurrencyPair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class BinanceOptionMarketDataServiceTest {

    Logger log= LoggerFactory.getLogger(BinancePerpetualMarketDataServiceTest.class);

    @Test
    public void optionInfo() {
        MHExchange exchange= MHExchangeFactory.INSTANCE.createExchange(BinanceExchange.class
                , TradingArea.Option);
        StreamingParsingCurrencyPair parsing=exchange.getStreamingParsing().parsingCurrencyPair;
        BinanceOptionMarketDataService mhMarketDataService = (BinanceOptionMarketDataService) exchange.getMarketDataService();
        try {
            List<OptionInfo> optionInfos=mhMarketDataService.optionInfo();
            optionInfos.forEach(e->{
                log.info("{}",e);
            });
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }

}
