package org.market.hedge.deribit.option.service;

import org.market.hedge.core.ParsingCurrencyPair;
import org.market.hedge.core.PositionInfo;
import org.market.hedge.deribit.DeribitExchange;
import org.market.hedge.deribit.dto.account.Position;
import org.market.hedge.deribit.service.DeribitAccountServiceRaw;
import org.market.hedge.service.account.MHAccountService;

import java.io.IOException;

public class DeribitOptionAccountService extends DeribitAccountServiceRaw implements MHAccountService {

    public DeribitOptionAccountService(DeribitExchange exchange) {
        super(exchange);
    }

    @Override
    public PositionInfo getPosition(ParsingCurrencyPair parsingCurrencyPair, Object... args) throws IOException {
        Position position=deribitAuthenticated.getPosition(parsingCurrencyPair.getParsing(), deribitAuth).getResult();
        return PositionInfo.builder()
                .addSymbol(position.getInstrumentName())
                .addVolume(position.getSize())
                .addDirection(position.getDirection().name())
                .build();
    }
}
