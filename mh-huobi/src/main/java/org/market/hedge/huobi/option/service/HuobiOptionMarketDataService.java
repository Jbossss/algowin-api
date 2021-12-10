package org.market.hedge.huobi.option.service;

import org.knowm.xchange.Exchange;
import org.market.hedge.core.ParsingCurrencyPair;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.exceptions.ExchangeException;
import org.market.hedge.huobi.dto.marketdata.HuobiDepth;
import org.market.hedge.huobi.option.dto.marketdata.HuobiOptionContractInfo;
import org.market.hedge.huobi.option.dto.marketdata.HuobiOptionContractType;
import org.market.hedge.huobi.option.dto.marketdata.results.HuobiOptionContractInfoResult;
import org.market.hedge.service.marketdata.MHMarketDataService;


import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class HuobiOptionMarketDataService  extends HuobiOptionMarketDataServiceRaw implements MHMarketDataService {

    public HuobiOptionMarketDataService(Exchange exchange) {
        super(exchange);
    }

    @Override
    public OrderBook getOrderBook(ParsingCurrencyPair parsingCurrencyPair, Object... args) throws IOException {
        String depthType = "step0";
        if (args != null && args.length == 1) {
            Object arg0 = args[0];
            if (!(arg0 instanceof String)) {
                throw new ExchangeException("Argument 0 must be an String!");
            } else {
                depthType = (String) arg0;
            }
        }


        HuobiDepth depth = getHuobiDepth(parsingCurrencyPair, depthType);
        List<LimitOrder> bids =
                depth.getBids().entrySet().stream()
                        .map(
                                e ->
                                        new LimitOrder(
                                                Order.OrderType.BID, e.getValue(), parsingCurrencyPair.getCurrencyPair(), null, null, e.getKey()))
                        .collect(Collectors.toList());
        List<LimitOrder> asks =
                depth.getAsks().entrySet().stream()
                        .map(
                                e ->
                                        new LimitOrder(
                                                Order.OrderType.ASK, e.getValue(), parsingCurrencyPair.getCurrencyPair(), null, null, e.getKey()))
                        .collect(Collectors.toList());
        return new OrderBook(depth.getTs(), asks, bids);
    }

    public HuobiOptionContractInfo[]  getOptionContractInfo(String symbol,
                                                            String trade_partition,
                                                            HuobiOptionContractType contract_type,
                                                            String contract_code) throws IOException {

        HuobiOptionContractInfoResult huobiOptionContractInfoResult=huobiOption.getOptionContractInfo(
                //false		品种代码	"BTC","ETH"，如果缺省，默认返回所有品种
                symbol,
                //	false		交易分区，不填默认”USDT“	"USDT"
                trade_partition,
                //	false		合约类型	当周:"this_week", 次周:"next_week", 季度:"quarter"
                contract_type.getValue(),
                //	false		合约代码	BTC-USDT-200508-C-8800)
                contract_code);

        return checkResult(huobiOptionContractInfoResult);
    }
}
