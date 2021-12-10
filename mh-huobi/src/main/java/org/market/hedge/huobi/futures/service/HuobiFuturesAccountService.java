package org.market.hedge.huobi.futures.service;

import org.knowm.xchange.Exchange;
import org.market.hedge.huobi.HuobiUtils;
import org.market.hedge.core.BilateralPositionInfo;
import org.market.hedge.core.ParsingCurrencyPair;
import org.market.hedge.core.PositionInfo;
import org.market.hedge.huobi.futures.dto.account.HuobiFuturesPositionInfoRequest;
import org.market.hedge.huobi.futures.dto.account.results.HuobiFuturesPositionInfoResult;
import org.market.hedge.huobi.service.HuobiDigest;
import org.market.hedge.service.account.MHAccountService;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

public class HuobiFuturesAccountService extends HuobiFuturesAccountServiceRaw implements MHAccountService {

    public HuobiFuturesAccountService(Exchange exchange) {
        super(exchange);
    }


    @Override
    public BilateralPositionInfo getBilateralPosition(ParsingCurrencyPair parsingCurrencyPair, Object... args) throws IOException {
        HuobiFuturesPositionInfoResult results=huobiFutures.contractPositionInfo(
                new HuobiFuturesPositionInfoRequest(parsingCurrencyPair.getCurrencyPair().base.toString()),
                exchange.getExchangeSpecification().getApiKey(),
                HuobiDigest.HMAC_SHA_256,
                2,
                HuobiUtils.createUTCDate(exchange.getNonceFactory()),
                signatureCreator);
        AtomicReference<PositionInfo> buy_position=new AtomicReference<>();
        AtomicReference<PositionInfo> sell_position=new AtomicReference<>();
        results.getData().forEach(e->{
            if (e.getContract_code().equals(parsingCurrencyPair.getParsing())){   //"buy":买 "sell":卖
                if (e.getDirection().equals("buy")){
                    buy_position.set(PositionInfo.builder()
                            .addAvailable(e.getAvailable())
                            .addContract_code(e.getContract_code())
                            .addCost_hold(e.getCost_hold())
                            .addCost_open(e.getCost_open())
                            .addDirection(e.getDirection())
                            .addFrozen(e.getFrozen())
                            .addLever_rate(e.getLever_rate())
                            .addPosition_margin(e.getPosition_margin())
                            .addProfit(e.getProfit())
                            .addProfit_rate(e.getProfit_rate())
                            .addProfit_unreal(e.getProfit_unreal())
                            .addSymbol(e.getSymbol())
                            .addVolume(e.getVolume())
                            .build());
                }else if (e.getDirection().equals("sell")){
                    sell_position.set(PositionInfo.builder()
                            .addAvailable(e.getAvailable())
                            .addContract_code(e.getContract_code())
                            .addCost_hold(e.getCost_hold())
                            .addCost_open(e.getCost_open())
                            .addDirection(e.getDirection())
                            .addFrozen(e.getFrozen())
                            .addLever_rate(e.getLever_rate())
                            .addPosition_margin(e.getPosition_margin())
                            .addProfit(e.getProfit())
                            .addProfit_rate(e.getProfit_rate())
                            .addProfit_unreal(e.getProfit_unreal())
                            .addSymbol(e.getSymbol())
                            .addVolume(e.getVolume())
                            .build());
                }

            }
        });
        return new BilateralPositionInfo(buy_position.get(),sell_position.get());
    }
}
