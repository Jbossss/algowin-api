package org.market.hedge.huobi.option.dto.trader.results;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HuobiOptionOrderResult {

    private final Integer index;  //订单索引
    private final Long order_id;  //订单ID
    private final String order_id_str;  //String类型订单ID
    private final Long client_order_id;  //用户下单时填写的客户端订单ID，没填则不返回

    public Integer getIndex() {
        return index;
    }

    public Long getOrder_id() {
        return order_id;
    }

    public String getOrder_id_str() {
        return order_id_str;
    }

    public Long getClient_order_id() {
        return client_order_id;
    }

    public HuobiOptionOrderResult(
            @JsonProperty("index") Integer index,
            @JsonProperty("order_id") Long order_id,
            @JsonProperty("order_id_str") String order_id_str,
            @JsonProperty("client_order_id") Long client_order_id) {
        this.index = index;
        this.order_id = order_id;
        this.order_id_str = order_id_str;
        this.client_order_id = client_order_id;
    }
}
