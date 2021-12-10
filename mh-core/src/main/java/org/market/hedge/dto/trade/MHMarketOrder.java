package org.market.hedge.dto.trade;

import org.knowm.xchange.dto.trade.MarketOrder;
import org.knowm.xchange.instrument.Instrument;
import org.market.hedge.core.ParsingCurrencyPair;

import java.math.BigDecimal;
import java.util.Date;

public class MHMarketOrder extends MarketOrder {

    protected final ParsingCurrencyPair parsingCurrencyPair;

    public ParsingCurrencyPair getParsingCurrencyPair() {
        return parsingCurrencyPair;
    }

    public MHMarketOrder(OrderType type, BigDecimal originalAmount, Instrument instrument, String id, Date timestamp, BigDecimal averagePrice, BigDecimal cumulativeAmount, BigDecimal fee, OrderStatus status, String userReference, ParsingCurrencyPair parsing) {
        super(type, originalAmount, instrument, id, timestamp, averagePrice, cumulativeAmount, fee, status, userReference);
        this.parsingCurrencyPair = parsing;
    }

    public MHMarketOrder(OrderType type, BigDecimal originalAmount, Instrument instrument, String id, Date timestamp, BigDecimal averagePrice, BigDecimal cumulativeAmount, BigDecimal fee, OrderStatus status, ParsingCurrencyPair parsing) {
        super(type, originalAmount, instrument, id, timestamp, averagePrice, cumulativeAmount, fee, status);
        this.parsingCurrencyPair = parsing;
    }

    public MHMarketOrder(OrderType type, BigDecimal originalAmount, Instrument instrument, String id, Date timestamp, ParsingCurrencyPair parsing) {
        super(type, originalAmount, instrument, id, timestamp);
        this.parsingCurrencyPair = parsing;
    }

    public MHMarketOrder(OrderType type, BigDecimal originalAmount, Instrument instrument, Date timestamp, ParsingCurrencyPair parsing) {
        super(type, originalAmount, instrument, timestamp);
        this.parsingCurrencyPair = parsing;
    }

    public MHMarketOrder(OrderType type, BigDecimal originalAmount, Instrument instrument, ParsingCurrencyPair parsing) {
        super(type, originalAmount, instrument);
        this.parsingCurrencyPair = parsing;
    }


}
