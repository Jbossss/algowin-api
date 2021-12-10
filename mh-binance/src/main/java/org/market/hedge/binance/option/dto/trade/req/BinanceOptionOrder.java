package org.market.hedge.binance.option.dto.trade.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import org.market.hedge.binance.dto.trade.OrderSide;
import org.market.hedge.binance.dto.trade.OrderType;
import org.market.hedge.binance.dto.trade.TimeInForce;

import java.math.BigDecimal;

@Data
@Builder
public class BinanceOptionOrder {

    /**
     * STRING	YES	期权交易对	BTC-200730-9000-C
     */
    @JsonProperty("symbol")
    private String symbol;

    /**
     * ENUM	YES	买卖方向 SELL, BUY	BUY
     */
    @JsonProperty("side")
    private OrderSide side;

    /**
     *	ENUM	YES	订单类型 LIMIT, MARKET	LIMIT
     */
    @JsonProperty("type")
    private OrderType type;

    /**
     *	DECIMAL	YES	下单数量	3
     */
    @JsonProperty("quantity")
    private BigDecimal quantity;

    /**
     *	DECIMAL	NO	委托价格	1000
     */
    @JsonProperty("price")
    private BigDecimal price;

    /**
     *	ENUM	NO	有效方法（默认GTC）	GTC
     */
    @JsonProperty("timeInForce")
    private TimeInForce timeInForce;

    /**
     *	BOOLEAN	NO	是否仅平仓单（默认false）	false
     */
    @JsonProperty("reduceOnly")
    private Boolean reduceOnly;

    /**
     *	BOOLEAN	NO	是否仅被动成交（默认false）	false
     */
    @JsonProperty("postOnly")
    private String postOnly;

    /**
     *	ENUM	NO	"ACK", "RESULT", 默认 "ACK"	ACK
     */
    @JsonProperty("newOrderRespType")
    private String newOrderRespType;

    /**
     *	STRING	NO	用户自定义的订单号，不可以重复出现在挂单中
     */
    @JsonProperty("clientOrderId")
    private String clientOrderId;
}
