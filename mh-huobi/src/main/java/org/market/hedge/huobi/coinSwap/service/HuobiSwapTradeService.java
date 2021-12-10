package org.market.hedge.huobi.coinSwap.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.exceptions.ExchangeException;
import org.market.hedge.huobi.HuobiUtils;
import org.market.hedge.dto.trade.MHLimitOrder;
import org.market.hedge.huobi.service.HuobiDigest;
import org.market.hedge.service.trade.MHTradeService;
import org.market.hedge.huobi.option.dto.trader.results.HuobiOptionPlaceOrdersResult;
import org.market.hedge.huobi.coinSwap.dto.trader.HuobiSwapCreateBatchOrderRequest;
import org.market.hedge.huobi.coinSwap.dto.trader.HuobiSwapCreateOrderRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HuobiSwapTradeService extends HuobiSwapTradeServiceRaw implements MHTradeService {

    public HuobiSwapTradeService(Exchange exchange) {
        super(exchange);
    }

    @Override
    public List<String> placeLimitOrders(List<MHLimitOrder> LimitOrders) throws IOException {
        List<HuobiSwapCreateOrderRequest> ordersData=new ArrayList<>();
        for (MHLimitOrder LimitOrder:LimitOrders){
            ordersData.add(getHuobiSwapCreateOrderRequest(LimitOrder));
        }

        HuobiOptionPlaceOrdersResult result =
                huobiSwap.placeLimitOrders(
                        new HuobiSwapCreateBatchOrderRequest(ordersData),
                        exchange.getExchangeSpecification().getApiKey(),
                        HuobiDigest.HMAC_SHA_256,
                        2,
                        HuobiUtils.createUTCDate(exchange.getNonceFactory()),
                        signatureCreator);
        List<String>  res=new ArrayList<>();

        checkResult(result).getSuccess().forEach(e->{
            res.add(e.getOrder_id().toString());
        });
        return res;
    }

    public HuobiSwapCreateOrderRequest getHuobiSwapCreateOrderRequest(MHLimitOrder LimitOrder){
        String type,offset;
        if (LimitOrder.getType() == Order.OrderType.BID) {
            type = "buy";
            offset="open";
        } else if (LimitOrder.getType() == Order.OrderType.ASK) {
            type = "sell";
            offset="open";
        } else if (LimitOrder.getType() == Order.OrderType.EXIT_BID) {
            type = "buy";
            offset="close";
        } else if (LimitOrder.getType() == Order.OrderType.EXIT_ASK) {
            type = "sell";
            offset="close";
        } else {
            throw new ExchangeException("Unsupported order type.");
        }

        return new HuobiSwapCreateOrderRequest(
                LimitOrder.getParsingCurrencyPair().getParsing(),
                LimitOrder.getUserReference(),
                LimitOrder.getLimitPrice().toString(),
                LimitOrder.getOriginalAmount().toString(),
                type,
                offset,
                Integer.parseInt(LimitOrder.getLeverage()),
                "limit");
    }

}
