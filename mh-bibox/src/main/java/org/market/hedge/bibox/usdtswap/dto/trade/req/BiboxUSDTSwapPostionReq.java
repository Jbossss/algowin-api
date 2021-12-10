package org.market.hedge.bibox.usdtswap.dto.trade.req;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BiboxUSDTSwapPostionReq {

    /**
     *  true	integer	订单类型		1:市价,2:限价
     */
    @JsonProperty("order_type")
    Integer order_type;


    /**
     * true	integer	杠杆倍数		全仓:0,逐仓:1,2,...
     */
    @JsonProperty("leverage")
    Integer leverage;

    /**
     * false	integer	全仓杠杆	25	leverage=0时,且全仓:1,2,...50
     */
    @JsonProperty("cross_leverage")
    Integer cross_leverage;

    /**
     * true	integer	挂单方向		1:开多,2:开空
     */
    @JsonProperty("order_side")
    Integer order_side;

    /**
     * true	string	委托价格		大于0的数
     */
    @JsonProperty("price")
    String price;

    /**
     * true	string	合约张数		1,2, ...
     */
    @JsonProperty("contract")
    String contract;

    /**
     * true	string	合约符号		4BTC_USDT,4ETH_USDT, ...
     */
    @JsonProperty("pair")
    String pair;

    /**
     * true	integer	6		6
     */
    @JsonProperty("order_from")
    Integer order_from;

    /**
     * false	Long	自定义标识		> 0
     */
    @JsonProperty("client_oid")
    Long client_oid;



    public BiboxUSDTSwapPostionReq(Integer order_type, Integer leverage, Integer cross_leverage, Integer order_side, String price, String contract, String pair, Integer order_from, Long client_oid) {
        this.order_type = order_type;
        this.leverage = leverage;
        this.cross_leverage = cross_leverage;
        this.order_side = order_side;
        this.price = price;
        this.contract = contract;
        this.pair = pair;
        this.order_from = order_from;
        this.client_oid = client_oid;
    }

    public Integer getOrder_type() {
        return order_type;
    }

    public Integer getLeverage() {
        return leverage;
    }

    public Integer getCross_leverage() {
        return cross_leverage;
    }

    public Integer getOrder_side() {
        return order_side;
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

    public Integer getOrder_from() {
        return order_from;
    }

    public Long getClient_oid() {
        return client_oid;
    }


}
