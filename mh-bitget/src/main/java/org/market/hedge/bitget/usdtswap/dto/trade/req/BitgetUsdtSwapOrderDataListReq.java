package org.market.hedge.bitget.usdtswap.dto.trade.req;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * symbol
 * orderDataList	（price=5,size=2,type=1,match_price=1,order_type=1,client_oid="abc")
 */
public class BitgetUsdtSwapOrderDataListReq {
    @JsonProperty("price")
    protected  String price;
    @JsonProperty("size")
    protected  String size;
    @JsonProperty("type")
    protected  String type;
    @JsonProperty("match_price")
    protected  String match_price;
    @JsonProperty("order_type")
    protected  String order_type;
    @JsonProperty("client_oid")
    protected  String client_oid;

    public BitgetUsdtSwapOrderDataListReq(String price, String size, String type, String match_price, String order_type, String client_oid) {
        this.price = price;
        this.size = size;
        this.type = type;
        this.match_price = match_price;
        this.order_type = order_type;
        this.client_oid = client_oid;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setMatch_price(String match_price) {
        this.match_price = match_price;
    }

    public void setOrder_type(String order_type) {
        this.order_type = order_type;
    }

    public void setClient_oid(String client_oid) {
        this.client_oid = client_oid;
    }

    public String getPrice() {
        return price;
    }

    public String getSize() {
        return size;
    }

    public String getType() {
        return type;
    }

    public String getMatch_price() {
        return match_price;
    }

    public String getOrder_type() {
        return order_type;
    }

    public String getClient_oid() {
        return client_oid;
    }
}
