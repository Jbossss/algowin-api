package org.market.hedge.binance.perpetualSwap.service;

import com.alibaba.fastjson.JSON;
import org.knowm.xchange.client.ResilienceRegistries;
import org.knowm.xchange.dto.Order;
import org.market.hedge.binance.BinanceAdapters;
import org.market.hedge.binance.BinanceErrorAdapter;
import org.market.hedge.binance.BinanceExchange;
import org.market.hedge.binance.dto.BinanceException;
import org.market.hedge.binance.dto.trade.*;
import org.market.hedge.binance.perpetualSwap.BinancePerpetualAuthenticated;
import org.market.hedge.binance.perpetualSwap.dto.trade.req.BinancePerpetualLeverage;
import org.market.hedge.binance.perpetualSwap.dto.trade.req.BinancePerpetualOrder;
import org.market.hedge.binance.perpetualSwap.dto.trade.req.Binanceresult;
import org.market.hedge.binance.service.BinanceTradeService;
import org.market.hedge.core.ParsingCurrencyPair;
import org.market.hedge.dto.trade.MHLimitOrder;
import org.market.hedge.dto.trade.MHMarketOrder;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.knowm.xchange.client.ResilienceRegistries.NON_IDEMPOTENTE_CALLS_RETRY_CONFIG_NAME;
import static org.market.hedge.binance.BinanceResilience.*;
import java.util.*;

public class BinancePerpetualTradeServiceRaw extends BinancePerpetualBaseService{

    protected BinancePerpetualTradeServiceRaw(BinanceExchange exchange, BinancePerpetualAuthenticated binance, ResilienceRegistries resilienceRegistries) {
        super(exchange, binance, resilienceRegistries);
    }


    protected String placeOrdersLimit(List<MHLimitOrder> limitOrders) throws IOException {
        OrderType type=OrderType.LIMIT;
        List<BinancePerpetualOrder> batchOrders=new ArrayList<BinancePerpetualOrder>();
        limitOrders.forEach(e->{
            BinancePerpetualOrder newOrder =
                    newOrder(
                            e.getParsingCurrencyPair(),
                            BinanceAdapters.convert(e.getType()),
                            type,
                            null,
                            e.getOriginalAmount(),
                            e.getLimitPrice(),
                            getClientOrderId(e),
                            null);
            batchOrders.add(newOrder);
        });
        BinancePerpetualOrder[] strings = new BinancePerpetualOrder[batchOrders.size()];
        batchOrders.toArray(strings);
        try {
            binance.batchOrders(JSON.toJSONString(strings),getTimestampFactory().createValue(),getTimestampFactory(),apiKey,signatureCreator);
            return "success";
        } catch (BinanceException e) {
            throw BinanceErrorAdapter.adapt(e);
        }
    }

    protected String placeOrdersMarket(List<MHMarketOrder> marketOrder)
            throws IOException {
        OrderType type=OrderType.MARKET;
        List<BinancePerpetualOrder> batchOrders=new ArrayList<BinancePerpetualOrder>();
        marketOrder.forEach(e->{
            BinancePerpetualOrder newOrder =
                    newOrder(
                            e.getParsingCurrencyPair(),
                            BinanceAdapters.convert(e.getType()),
                            type,
                            null,
                            e.getOriginalAmount(),
                            null,
                            getClientOrderId(e),
                            null);
            batchOrders.add(newOrder);
        });
        try {
            BinancePerpetualOrder[] strings = new BinancePerpetualOrder[batchOrders.size()];
            batchOrders.toArray(strings);
            binance.batchOrders(JSON.toJSONString(strings),getRecvWindow(),getTimestampFactory(),apiKey,signatureCreator);
            return "success";
        } catch (BinanceException e) {
            throw BinanceErrorAdapter.adapt(e);
        }
    }

    private String getClientOrderId(Order order) {

        String clientOrderId = null;
        for (Order.IOrderFlags flags : order.getOrderFlags()) {
            if (flags instanceof BinanceTradeService.BinanceOrderFlags) {
                BinanceTradeService.BinanceOrderFlags bof = (BinanceTradeService.BinanceOrderFlags) flags;
                if (clientOrderId == null) {
                    clientOrderId = bof.getClientId();
                }
            }
        }
        return clientOrderId;
    }

    public BinancePerpetualOrder newOrder(
            ParsingCurrencyPair pair,
            OrderSide side,
            OrderType type,
            TimeInForce timeInForce,
            BigDecimal quantity,
            BigDecimal price,
            String newClientOrderId,
            BigDecimal stopPrice) {

        return new BinancePerpetualOrder(
                pair.getParsing(),
                side,
                type,
                timeInForce,
                quantity,
                price,
                newClientOrderId,
                stopPrice);

    }

    public  boolean getPositionSideDual(Long recvWindow) throws IOException {
        return binance.getPositionSideDual(getTimestampFactory(),null,apiKey,signatureCreator);
    }

    public HashMap setPositionSideDual(String dualSidePosition, Long recvWindow) throws IOException {
        return binance.setPositionSideDual(dualSidePosition,recvWindow,getTimestampFactory(),apiKey,signatureCreator);

    }

    public Binanceresult setMarginType(ParsingCurrencyPair pair, MarginType marginType) throws IOException {
        return binance.setMarginType(pair.getParsing(),marginType,null,getTimestampFactory(),apiKey,signatureCreator);

    }

    public BinancePerpetualLeverage setLeverageRaw(ParsingCurrencyPair pair, Integer leverage) throws IOException {
        return binance.setLeverage(pair.getParsing(),leverage,null,getTimestampFactory(),apiKey,signatureCreator);
    }


}
