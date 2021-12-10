package org.market.hedge.deribit.option.service;

import org.knowm.xchange.dto.Order;
import org.market.hedge.core.ParsingCurrencyPair;
import org.market.hedge.deribit.DeribitExchange;
import org.market.hedge.deribit.dto.trade.OrderPlacement;
import org.market.hedge.deribit.dto.trade.OrderType;
import org.market.hedge.deribit.service.DeribitTradeServiceRaw;
import org.market.hedge.dto.trade.MHLimitOrder;
import org.market.hedge.service.trade.MHTradeService;

import java.io.IOException;
import java.util.List;

public class DeribitOptionTradeService extends DeribitTradeServiceRaw implements MHTradeService {

    public DeribitOptionTradeService(DeribitExchange exchange) {
        super(exchange);
    }


    @Override
    public String placeLimitOrder(MHLimitOrder limitOrder) throws IOException {
        OrderPlacement placement=null;
        OrderType type=null;
        switch (limitOrder.getType()){
            case BID:
            case ASK:
                type=OrderType.limit;
            case EXIT_ASK:
            case EXIT_BID:
                type=OrderType.stop_limit;

        }

        if (limitOrder.getType()== Order.OrderType.BID || limitOrder.getType()== Order.OrderType.EXIT_BID){
            placement=buy(limitOrder.getParsingCurrencyPair().getParsing(),
                    limitOrder.getOriginalAmount(),
                    type,
                    null,
                    limitOrder.getLimitPrice(),
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null);
        }else{
            placement=sell(limitOrder.getParsingCurrencyPair().getParsing(),
                    limitOrder.getOriginalAmount(),
                    type,
                    null,
                    limitOrder.getLimitPrice(),
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null);
        }
        return placement.getOrder().getOrderId();
    }

    @Override
    public List<String> placeLimitOrders(List<MHLimitOrder> LimitOrders) throws IOException {
        for (MHLimitOrder limitOrder : LimitOrders) {
            OrderType type=null;
            switch (limitOrder.getType()){
                case BID:
                case ASK:
                    type=OrderType.limit;
                case EXIT_ASK:
                case EXIT_BID:
                    type=OrderType.stop_limit;

            }
            if (limitOrder.getType()== Order.OrderType.BID || limitOrder.getType()== Order.OrderType.EXIT_BID){
                buy(limitOrder.getParsingCurrencyPair().getParsing(),
                        limitOrder.getOriginalAmount(),
                        null,
                        null,
                        limitOrder.getLimitPrice(),
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);
            }else{
                sell(limitOrder.getParsingCurrencyPair().getParsing(),
                        limitOrder.getOriginalAmount(),
                        null,
                        null,
                        limitOrder.getLimitPrice(),
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);
            }
        }
        return null;
    }

    @Override
    public void cancelAllByInstrument(ParsingCurrencyPair parsingCurrencyPair) throws IOException {
        deribitAuthenticated.cancelAllByInstrument(parsingCurrencyPair.getParsing(), null, deribitAuth);
    }

}
