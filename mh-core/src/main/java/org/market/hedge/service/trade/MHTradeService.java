package org.market.hedge.service.trade;

import org.knowm.xchange.exceptions.NotYetImplementedForExchangeException;
import org.knowm.xchange.service.trade.TradeService;
import org.market.hedge.core.ParsingCurrencyPair;
import org.market.hedge.dto.trade.MHLimitOrder;
import org.market.hedge.dto.trade.MHMarketOrder;

import java.io.IOException;
import java.util.List;

/**
 * 交易
 * Auth
 * */
public interface MHTradeService extends TradeService {

    /**
    * 下多个订单
    * */
    default List<String> placeLimitOrders(List<MHLimitOrder> limitOrders) throws IOException {
        throw new NotYetImplementedForExchangeException();
    }

    /**
     * 下单个
     * */
    default String placeLimitOrder(MHLimitOrder limitOrder) throws IOException {
        throw new NotYetImplementedForExchangeException();
    }

    /**
     * 撤销单个订单
     * */
    default String placeMarketOrder(MHMarketOrder marketOrder) throws IOException {
        throw new NotYetImplementedForExchangeException();
    }

    /**
     * 撤销单个订单
     * */
    default String placeMarketOrders(List<MHMarketOrder> marketOrder) throws IOException {
        throw new NotYetImplementedForExchangeException();
    }

    /**
     * 根据合约名称撤销关于此合约的所有订单
     * */
    default void cancelAllByInstrument(ParsingCurrencyPair parsingCurrencyPair) throws IOException {
        throw new NotYetImplementedForExchangeException();
    }


    /**
     * 调整开仓杠杆
     */
    default void setLeverage(ParsingCurrencyPair parsingCurrencyPair, Integer leverage)throws IOException {
        throw new NotYetImplementedForExchangeException();
    }
}
