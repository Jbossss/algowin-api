package org.market.hedge.deribit.option;

import org.junit.Test;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.account.AccountInfo;
import org.knowm.xchange.service.account.AccountService;
import org.market.hedge.MHExchange;
import org.market.hedge.MHExchangeFactory;
import org.market.hedge.core.Direction;
import org.market.hedge.core.ParsingCurrencyPair;
import org.market.hedge.core.TradingArea;
import org.market.hedge.deribit.DeribitExchange;
import org.market.hedge.dto.trade.MHLimitOrder;
import org.market.hedge.service.StreamingParsingCurrencyPair;
import org.market.hedge.service.account.MHAccountService;
import org.market.hedge.service.trade.MHTradeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DeribitOptionAccountTest {

    static Logger logger= LoggerFactory.getLogger(DeribitOptionTradeDataTest.class);

    @Test
    public void getPosition() {
        //MHExchange exchange = MHExchangeFactory.INSTANCE.createExchange(DeribitExchange.class,"xxxxxxxxx","xxxxxxxx", TradingArea.Option);
        MHExchange exchange = MHExchangeFactory.INSTANCE.createExchange(DeribitExchange.class,"xxxxxxxxx","xxxxxxxxxxx", TradingArea.Option);

        StreamingParsingCurrencyPair parsing=exchange.getStreamingParsing().parsingCurrencyPair;
        MHAccountService accountService=  exchange.getAccountService();
        ParsingCurrencyPair pair=parsing.parsing(CurrencyPair.BTC_USDT,new Date(1613116800000L),new BigDecimal("36000"), Direction.Call);
        try {
            accountService.getPosition(pair);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
