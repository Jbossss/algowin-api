package org.market.hedge.binance.perpetualSwap.dto.trade.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.market.hedge.binance.dto.trade.OrderSide;
import org.market.hedge.binance.dto.trade.OrderType;
import org.market.hedge.binance.dto.trade.TimeInForce;

import java.math.BigDecimal;

public class BinancePerpetualOrder {

    @JsonProperty("symbol")
    String symbol;
    @JsonProperty("side")
    OrderSide side;
    @JsonProperty("type")
    OrderType type;
    @JsonProperty("quantity")
    BigDecimal quantity;
    @JsonProperty("price")
    BigDecimal price;
    @JsonProperty("timeInForce")
    TimeInForce timeInForce;
    @JsonProperty("newClientOrderId")
    String newClientOrderId;
    @JsonProperty("stopPrice")
    BigDecimal stopPrice;

    public BinancePerpetualOrder(String symbol, OrderSide side, OrderType type, TimeInForce timeInForce, BigDecimal quantity, BigDecimal price, String newClientOrderId, BigDecimal stopPrice) {
        this.symbol = symbol;
        this.side = side;
        this.type = type;
        this.timeInForce = timeInForce;
        this.quantity = quantity;
        this.price = price;
        this.newClientOrderId = newClientOrderId;
        this.stopPrice = stopPrice;
    }

    public String getSymbol() {
        return symbol;
    }

    public OrderSide getSide() {
        return side;
    }

    public OrderType getType() {
        return type;
    }

    public TimeInForce getTimeInForce() {
        return timeInForce;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getNewClientOrderId() {
        return newClientOrderId;
    }

    public BigDecimal getStopPrice() {
        return stopPrice;
    }


}
