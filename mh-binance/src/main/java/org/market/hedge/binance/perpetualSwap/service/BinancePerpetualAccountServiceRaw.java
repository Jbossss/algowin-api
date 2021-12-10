package org.market.hedge.binance.perpetualSwap.service;

import org.knowm.xchange.client.ResilienceRegistries;
import org.market.hedge.binance.BinanceExchange;
import org.market.hedge.binance.perpetualSwap.BinancePerpetualAuthenticated;
import org.market.hedge.binance.perpetualSwap.dto.marketData.resp.BinancePositionInfo;
import org.market.hedge.core.PositionInfo;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class BinancePerpetualAccountServiceRaw extends BinancePerpetualBaseService{
    protected BinancePerpetualAccountServiceRaw(BinanceExchange exchange, BinancePerpetualAuthenticated binance, ResilienceRegistries resilienceRegistries) {
        super(exchange, binance, resilienceRegistries);
    }

    public PositionInfo getPositionRisk(String symbol, Long recvWindow) throws IOException {
        List<BinancePositionInfo> list = binance.positionRisk(symbol,recvWindow,getTimestampFactory(),apiKey,signatureCreator);
        if(list.size() == 1){
            PositionInfo.Builder builder = new PositionInfo.Builder();
            BinancePositionInfo binancePositionInfo = list.get(0);
            builder.addSymbol(binancePositionInfo.getSymbol());
            builder.addContract_code("");
            builder.addVolume(binancePositionInfo.getPositionAmt());
            builder.addAvailable(null);
            builder.addFrozen(null);
            builder.addCost_open(binancePositionInfo.getEntryPrice());
            builder.addCost_hold(binancePositionInfo.getEntryPrice());
            builder.addProfit_unreal(binancePositionInfo.getUnRealizedProfit());
            builder.addProfit(null);
            builder.addProfit_rate(null);
            builder.addPosition_margin(binancePositionInfo.getIsolatedMargin());
            builder.addLever_rate(binancePositionInfo.getLeverage());
            builder.addDirection(binancePositionInfo.getPositionAmt().compareTo(new BigDecimal(0))>0?"buy":"sell");

            return new PositionInfo(builder);
        }else{
            return null;
        }

    }
}
