package org.market.hedge.huobi.option.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.trade.MarketOrder;
import org.knowm.xchange.exceptions.ExchangeException;
import org.market.hedge.huobi.HuobiUtils;
import org.market.hedge.core.ParsingCurrencyPair;
import org.market.hedge.dto.trade.MHLimitOrder;

import org.market.hedge.huobi.option.dto.HuobiOptionContractCodeRequest;
import org.market.hedge.huobi.option.dto.HuobiOptionCreateOrderRequest;
import org.market.hedge.huobi.option.dto.HuobiOptionCreateOrdersRequest;
import org.market.hedge.huobi.option.dto.trader.HuobiOptionTransactionHistory;
import org.market.hedge.huobi.option.dto.trader.HuobiOptionTransactionHistoryRequest;
import org.market.hedge.huobi.option.dto.trader.results.HuobiOptionCancelAllResult;
import org.market.hedge.huobi.option.dto.trader.results.HuobiOptionPlaceOrderResult;
import org.market.hedge.huobi.option.dto.trader.results.HuobiOptionPlaceOrdersResult;
import org.market.hedge.huobi.option.dto.trader.results.HuobiOptionTransactionHistoryResult;
import org.market.hedge.huobi.service.HuobiDigest;
import org.market.hedge.service.trade.MHTradeService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HuobiOptionTradeService extends HuobiOptionTradeServiceRaw implements MHTradeService {

    public HuobiOptionTradeService(Exchange exchange) {
        super(exchange);
    }

    @Override
    public String placeLimitOrder(MHLimitOrder LimitOrder) throws IOException {
        HuobiOptionPlaceOrderResult result =
                huobiOption.placeLimitOrder(
                        getHuobiOptionCreateOrderRequest(LimitOrder),
                        exchange.getExchangeSpecification().getApiKey(),
                        HuobiDigest.HMAC_SHA_256,
                        2,
                        HuobiUtils.createUTCDate(exchange.getNonceFactory()),
                        signatureCreator);

        return checkResult(result).getOrder_id().toString();
    }

    public HuobiOptionCreateOrderRequest getHuobiOptionCreateOrderRequest(MHLimitOrder LimitOrder){
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

            return new HuobiOptionCreateOrderRequest(
                    LimitOrder.getParsingCurrencyPair().getParsing(),
                    LimitOrder.getUserReference(),
                    LimitOrder.getLimitPrice().toString(),
                    LimitOrder.getOriginalAmount().toString(),
                    type,
                    offset,
                    "limit");
    }

    @Override
    public List<String> placeLimitOrders(List<MHLimitOrder> LimitOrders) throws IOException {
        List<HuobiOptionCreateOrderRequest> ordersData=new ArrayList<>();
        for (MHLimitOrder LimitOrder:LimitOrders){
            ordersData.add(getHuobiOptionCreateOrderRequest(LimitOrder));
        }

        HuobiOptionPlaceOrdersResult result =
                huobiOption.placeLimitOrders(
                        new HuobiOptionCreateOrdersRequest(ordersData),
                        exchange.getExchangeSpecification().getApiKey(),
                        HuobiDigest.HMAC_SHA_256,
                        2,
                        HuobiUtils.createUTCDate(exchange.getNonceFactory()),
                        signatureCreator);
        List<String>  res=new ArrayList<>();

        if (!checkResultList(result)) {
            if (Objects.nonNull(result.getData().getErrors()) && result.getData().getErrors().size()>0){
                ObjectMapper mapper = new ObjectMapper();
                throw new ExchangeException(mapper.writeValueAsString(result.getData().getErrors()));
            }else {
                throw new ExchangeException("Missing error message");
            }
        }
        result.getData().getSuccess().forEach(e->{
            res.add(e.getOrder_id().toString());
        });
        return res;
    }

    @Override
    public String placeMarketOrder(MarketOrder marketOrder) throws IOException {
        return null;
    }

    @Override
    public void cancelAllByInstrument(ParsingCurrencyPair parsingCurrencyPair) throws IOException  {
        HuobiOptionCancelAllResult result=huobiOption.cancelAll(
                new HuobiOptionContractCodeRequest(parsingCurrencyPair.getCurrencyPair().base.getSymbol(),null,parsingCurrencyPair.getParsing()),
                exchange.getExchangeSpecification().getApiKey(),
                HuobiDigest.HMAC_SHA_256,
                2,
                HuobiUtils.createUTCDate(exchange.getNonceFactory()),
                signatureCreator
        );
    }


    public HuobiOptionTransactionHistory[] getTransactionHistory(String symbol,
                                                                 String trade_partition,
                                                                 Integer trade_type,
                                                                 Integer create_date,
                                                                 String contract_code,
                                                                 Integer page_index,
                                                                 Integer page_size) throws IOException {
        HuobiOptionTransactionHistoryResult result=huobiOption.getTransactionHistory(
                new HuobiOptionTransactionHistoryRequest( symbol,  trade_partition,  trade_type,  create_date,  contract_code,  page_index,  page_size),
                exchange.getExchangeSpecification().getApiKey(),
                HuobiDigest.HMAC_SHA_256,
                2,
                HuobiUtils.createUTCDate(exchange.getNonceFactory()),
                signatureCreator
        );
        return checkResult(result).getTrades();
    }


}
