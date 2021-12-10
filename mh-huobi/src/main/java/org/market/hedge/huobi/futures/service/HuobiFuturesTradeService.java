package org.market.hedge.huobi.futures.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.exceptions.ExchangeException;
import org.market.hedge.huobi.HuobiUtils;
import org.market.hedge.dto.trade.MHLimitOrder;
import org.market.hedge.huobi.option.dto.trader.results.HuobiOptionPlaceOrdersDataResult;
import org.market.hedge.huobi.option.dto.trader.results.HuobiOptionPlaceOrdersResult;
import org.market.hedge.huobi.service.HuobiDigest;
import org.market.hedge.service.trade.MHTradeService;
import org.market.hedge.huobi.futures.dto.trader.HuobiFuturesCreateBatchOrderRequest;
import org.market.hedge.huobi.futures.dto.trader.HuobiFuturesCreateOrderRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HuobiFuturesTradeService extends HuobiFuturesTradeServiceRaw implements MHTradeService {

    public HuobiFuturesTradeService(Exchange exchange) {
        super(exchange);
    }

    @Override
    public List<String> placeLimitOrders(List<MHLimitOrder> LimitOrders) throws IOException {
        List<HuobiFuturesCreateOrderRequest> ordersData=new ArrayList<>();
        for (MHLimitOrder LimitOrder:LimitOrders){
            ordersData.add(getHuobiFuturesCreateOrderRequest(LimitOrder));
        }

        HuobiOptionPlaceOrdersResult result =
                huobiFutures.placeLimitOrders(
                        new HuobiFuturesCreateBatchOrderRequest(ordersData),
                        exchange.getExchangeSpecification().getApiKey(),
                        HuobiDigest.HMAC_SHA_256,
                        2,
                        HuobiUtils.createUTCDate(exchange.getNonceFactory()),
                        signatureCreator);
        List<String>  res=new ArrayList<>();
        HuobiOptionPlaceOrdersDataResult rt=checkResult(result);

        if (Objects.nonNull(rt.getErrors())&&rt.getErrors().size()>0){
            rt.getErrors().forEach(e->{
                throw new ExchangeException(e.getErr_code()+": "+e.getErr_msg());
            });
        }
        rt.getSuccess().forEach(e->{
            res.add(e.getOrder_id().toString());
        });
        return res;
    }

    public HuobiFuturesCreateOrderRequest getHuobiFuturesCreateOrderRequest(MHLimitOrder LimitOrder){
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

        return new HuobiFuturesCreateOrderRequest(
                null,
                null,
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
