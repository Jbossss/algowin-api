package org.market.hedge.bitget.usdtswap.dto.trade.resp;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * {
 *     "result":true,
 *     "order_info":[
 *         {
 *             "result":true, //成交结果
 *             "client_oid":"dxdanzi", //客户端请求标识
 *             "order_id":"513468410013679613" //成交订单Id
 *         },
 *         {
 *             "result":true,
 *             "client_oid":"dxdanzi",
 *             "order_id":"513468410001096713"
 *         }
 *     ]
 * }
 */
public class BitgetUsdtSwapOrderinfo {
    @JsonProperty("result")
    protected  String result;
    @JsonProperty("client_oid")
    protected String client_oid;
    @JsonProperty("order_id")
    protected String order_id;

    public BitgetUsdtSwapOrderinfo(String result, String client_oid, String order_id) {
        this.result = result;
        this.client_oid = client_oid;
        this.order_id = order_id;
    }

    public String getResult() {
        return result;
    }

    public String getClient_oid() {
        return client_oid;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setClient_oid(String client_oid) {
        this.client_oid = client_oid;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }
}
