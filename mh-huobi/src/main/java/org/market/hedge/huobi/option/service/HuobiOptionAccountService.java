package org.market.hedge.huobi.option.service;

import org.knowm.xchange.Exchange;
import org.market.hedge.huobi.HuobiUtils;
import org.market.hedge.core.ParsingCurrencyPair;
import org.market.hedge.core.PositionInfo;
import org.market.hedge.huobi.option.dto.HuobiOptionContractCodeRequest;
import org.market.hedge.huobi.option.dto.account.results.HuobiOptionPositionResults;
import org.market.hedge.huobi.option.dto.account.results.HuobiOptionPositionsResults;
import org.market.hedge.huobi.service.HuobiDigest;
import org.market.hedge.service.account.MHAccountService;

import java.io.IOException;

public class HuobiOptionAccountService extends HuobiOptionAccountServiceRaw implements MHAccountService {

    public HuobiOptionAccountService(Exchange exchange) {
        super(exchange);
    }

    @Override
    public PositionInfo getPosition(ParsingCurrencyPair parsingCurrencyPair, Object... args) throws IOException {
        HuobiOptionPositionsResults results=huobiOption.getPosition(
                new HuobiOptionContractCodeRequest(null,null,parsingCurrencyPair.getParsing()),
                exchange.getExchangeSpecification().getApiKey(),
                HuobiDigest.HMAC_SHA_256,
                2,
                HuobiUtils.createUTCDate(exchange.getNonceFactory()),
                signatureCreator);
        if (results.getData().size()!=0){
            HuobiOptionPositionResults positionResults =results.getData().get(0);
            return PositionInfo.builder()
                    .addAvailable(positionResults.getAvailable())
                    .addContract_code(positionResults.getContract_code())
                    .addCost_hold(positionResults.getCost_hold())
                    .addCost_open(positionResults.getCost_open())
                    .addDirection(positionResults.getDirection())
                    .addFrozen(positionResults.getFrozen())
                    .addProfit(positionResults.getProfit())
                    .addProfit_rate(positionResults.getProfit_rate())
                    .addProfit_unreal(positionResults.getProfit_unreal())
                    .addSymbol(positionResults.getSymbol())
                    .addVolume(positionResults.getVolume())
                    .build();
        }else {
            return null;
        }

    }




}
