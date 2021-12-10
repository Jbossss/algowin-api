package org.market.hedge.huobi.usdtSwap.service;

import org.knowm.xchange.Exchange;
import org.market.hedge.service.trade.MHTradeService;

public class HuobiUsdtSwapTradeService extends HuobiUsdtSwapTradeServiceRaw implements MHTradeService {

    public HuobiUsdtSwapTradeService(Exchange exchange) {
        super(exchange);
    }



}
