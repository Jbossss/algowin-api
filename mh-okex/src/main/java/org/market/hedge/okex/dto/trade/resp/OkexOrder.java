package org.market.hedge.okex.dto.trade.resp;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OkexOrder {

    /**
     * 订单ID
     */
    @JsonProperty("ordId")
    String ordId;

    /**
     * 客户自定义订单ID
     */
    @JsonProperty("clOrdId")
    String clOrdId;
    /**
     * 订单标签
     */
    @JsonProperty("tag")
    String tag;
    /**
     * 事件执行结果的code，0代表成功
     */
    @JsonProperty("sCode")
    String sCode;

    /**
     * 事件执行失败时的msg
     */
    @JsonProperty("sMsg")
    String sMsg;

    public void setOrdId(String ordId) {
        this.ordId = ordId;
    }

    public void setClOrdId(String clOrdId) {
        this.clOrdId = clOrdId;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setsCode(String sCode) {
        this.sCode = sCode;
    }

    public void setsMsg(String sMsg) {
        this.sMsg = sMsg;
    }

    public String getOrdId() {
        return ordId;
    }

    public String getClOrdId() {
        return clOrdId;
    }

    public String getTag() {
        return tag;
    }

    public String getsCode() {
        return sCode;
    }

    public String getsMsg() {
        return sMsg;
    }

}
