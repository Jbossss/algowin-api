package org.market.hedge.bibox.usdtswap.dto.trade.req;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @ClassName: PlaceLimitOrderReqBo
 * @Version: 1.0
 * @Desc: Bibox下一个订单的请求参数
 * @author: liusongqi
 * @Date: 2020/11/12 13:56
 */
public class BiboxUSDTSwapOrderReqBo {

    /**
     * 合约符号
     */
    @JsonProperty("pair")
    private String pair;

    /**
     * 数量转字符串
     */
    @JsonProperty("contract")
    private String amount;

    /**
     * 下单类型,1开多,2开空,3平多,4平空
     */
    @JsonProperty("order_side")
    private Integer orderSide;

    /**
     * 下单方式,1市价单,2限价单
     */
    @JsonProperty("order_type")
    private Integer orderType;

    /**
     * 下单价格
     */
    @JsonProperty("price")
    private String price;

    /**
     * 下单来源
     */
    @JsonProperty("order_from")
    private Integer orderFrom;

    /**
     * 自定义id
     */
    @JsonProperty("client_oid")
    private String clientOid;

    public String getPair() {
        return pair;
    }

    public String getAmount() {
        return amount;
    }

    public Integer getOrderSide() {
        return orderSide;
    }

    public BiboxUSDTSwapOrderReqBo(String pair, String amount, Integer orderSide, Integer orderType, String price, Integer orderFrom, String clientOid) {
        super();
        this.pair = pair;
        this.amount = amount;
        this.orderSide = orderSide;
        this.orderType = orderType;
        this.price = price;
        this.orderFrom = orderFrom;
        this.clientOid = clientOid;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public String getPrice() {
        return price;
    }

    public Integer getOrderFrom() {
        return orderFrom;
    }

    public String getClientOid() {
        return clientOid;
    }
}
