package org.market.hedge.bitget.usdtswap.dto.trade.resp;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.vavr.collection.List;

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
public class BitgetUsdtSwapSingleResponse {

    @JsonProperty("result")
    protected  String result;
    @JsonProperty("order_info")
    protected List<BitgetUsdtSwapOrderinfo> order_info;

    public BitgetUsdtSwapSingleResponse(String result, List<BitgetUsdtSwapOrderinfo> order_info) {
        this.result = result;
        this.order_info = order_info;
    }

    public String getResult() {
        return result;
    }

    public List<BitgetUsdtSwapOrderinfo> getOrder_info() {
        return order_info;
    }
}
