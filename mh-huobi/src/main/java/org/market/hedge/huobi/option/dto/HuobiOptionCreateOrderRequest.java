package org.market.hedge.huobi.option.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class HuobiOptionCreateOrderRequest {


    @JsonProperty("contract_code")
    public String contractCode;

    @JsonProperty("client-order-id")
    public String clOrdId;

    @JsonProperty("price")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public String price;

    @JsonProperty("volume")
    public String volume;

    @JsonProperty("direction")
    public String direction;

    @JsonProperty("offset")
    public String offset;

    @JsonProperty("order_price_type")
    public String orderPriceType;

    public HuobiOptionCreateOrderRequest(
            String contractCode,
            String clOrdId,
            String price,
            String volume,
            String direction,
            String offset,
            String orderPriceType) {
        this.contractCode = contractCode;
        this.clOrdId = clOrdId;
        this.price = price;
        this.volume = volume;
        this.direction = direction;
        this.offset = offset;
        this.orderPriceType = orderPriceType;
    }

    public String getContractCode() {
        return contractCode;
    }

    public String getClOrdId() {
        return clOrdId;
    }

    public String getPrice() {
        return price;
    }

    public String getVolume() {
        return volume;
    }

    public String getDirection() {
        return direction;
    }

    public String getOffset() {
        return offset;
    }

    public String getOrderPriceType() {
        return orderPriceType;
    }



}
