package org.market.hedge.dto.trade;

import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.instrument.Instrument;
import org.market.hedge.core.ParsingCurrencyPair;

import java.math.BigDecimal;
import java.util.Date;

public class MHLimitOrder extends LimitOrder {

    protected final ParsingCurrencyPair parsingCurrencyPair;

    public ParsingCurrencyPair getParsingCurrencyPair() {
        return parsingCurrencyPair;
    }

    public MHLimitOrder(OrderType type, BigDecimal originalAmount, Instrument instrument, String id, Date timestamp, BigDecimal limitPrice, ParsingCurrencyPair parsing) {
        super(type, originalAmount, instrument, id, timestamp, limitPrice);
        this.parsingCurrencyPair = parsing;
    }

    public MHLimitOrder(OrderType type, BigDecimal originalAmount, BigDecimal cumulativeAmount, Instrument instrument, String id, Date timestamp, BigDecimal limitPrice, ParsingCurrencyPair parsing) {
        super(type, originalAmount, cumulativeAmount, instrument, id, timestamp, limitPrice);
        this.parsingCurrencyPair = parsing;
    }

    public MHLimitOrder(OrderType type, BigDecimal originalAmount, Instrument instrument, String id, Date timestamp, BigDecimal limitPrice, BigDecimal averagePrice, BigDecimal cumulativeAmount, BigDecimal fee, OrderStatus status, ParsingCurrencyPair parsing) {
        super(type, originalAmount, instrument, id, timestamp, limitPrice, averagePrice, cumulativeAmount, fee, status);
        this.parsingCurrencyPair = parsing;
    }

    public MHLimitOrder(OrderType type, BigDecimal originalAmount, Instrument instrument, String id, Date timestamp, BigDecimal limitPrice, BigDecimal averagePrice, BigDecimal cumulativeAmount, BigDecimal fee, OrderStatus status, String userReference, ParsingCurrencyPair parsing) {
        super(type, originalAmount, instrument, id, timestamp, limitPrice, averagePrice, cumulativeAmount, fee, status, userReference);
        this.parsingCurrencyPair = parsing;
    }
}
