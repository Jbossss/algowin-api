package org.market.hedge.bibox.usdtswap.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

public class BiboxUSDTTradeCommandBody {

    /**
     * 	true		订单类型		1:市价,2:限价
     */
    @JsonProperty("order_type")
    Integer orderType;
    /**
     * 	true	integer	杠杆倍数		全仓:0,逐仓:1,2,...
     */
    @JsonProperty("leverage")
    Integer leverage;

    /**
     *	false	integer	全仓杠杆	25	leverage=0时,且全仓:1,2,...50
     */
    @JsonProperty("cross_leverage")
    Integer crossLeverage;

    /**
     *	true	integer	挂单方向		1:开多,2:开空
     */
    @JsonProperty("order_side")
    Integer orderSide;

    /**
     *	true	string	委托价格		大于0的数
     */
    @JsonProperty("price")
    String price;

    /**
     *	true	string	合约张数		1,2, ...
     */
    @JsonProperty("contract")
    String contract;

    /**
     *	true	string	合约符号		4BTC_USDT,4ETH_USDT, ...
     */
    @JsonProperty("pair")
    String pair;

    /**
     *	true	integer	6		6
     */
    @JsonProperty("order_from")
    Integer orderFrom;

    /**
     *	false	Long	自定义标识		> 0
     */
    @JsonProperty("client_oid")
    Long clientOid;

    public BiboxUSDTTradeCommandBody(Integer orderType, Integer leverage, Integer crossLeverage, Integer orderSide, String price, String contract, String pair, Integer orderFrom, Long clientOid) {
        super();
        this.orderType = orderType;
        this.leverage = leverage;
        this.crossLeverage = crossLeverage;
        this.orderSide = orderSide;
        this.price = price;
        this.contract = contract;
        this.pair = pair;
        this.orderFrom = orderFrom;
        this.clientOid = clientOid;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public Integer getLeverage() {
        return leverage;
    }

    public Integer getCrossLeverage() {
        return crossLeverage;
    }

    public Integer getOrderSide() {
        return orderSide;
    }

    public String getPrice() {
        return price;
    }

    public String getContract() {
        return contract;
    }

    public String getPair() {
        return pair;
    }

    public Integer getOrderFrom() {
        return orderFrom;
    }

    public Long getClientOid() {
        return clientOid;
    }
}
