package org.market.hedge.huobi.futures.dto.trader;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class HuobiFuturesCreateOrderRequest {

    @JsonProperty("symbol")
    public String symbol;		//false	支持大小写,"BTC","ETH"...

    @JsonProperty("contract_type")
    public String contract_type;	//string	false	合约类型: "this_week":当周 "next_week":下周 "quarter":当季 "next_quarter":次季

    @JsonProperty("contract_code")
    public String contractCode;

    @JsonProperty("client_order_id")
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

    @JsonProperty("leverRate")
    public Integer lever_rate;	//int	true	杠杆倍数[“开仓”若有10倍多单，就不能再下20倍多单;首次使用高倍杠杆(>20倍)，请使用主账号登录web端同意高倍杠杆协议后，才能使用接口下高倍杠杆(>20倍)]

    @JsonProperty("orderPriceType")
    public String orderPriceType;

    public HuobiFuturesCreateOrderRequest(String symbol, String contract_type, String contractCode, String clOrdId, String price, String volume, String direction, String offset, Integer lever_rate, String orderPriceType) {
        this.symbol = symbol;
        this.contract_type = contract_type;
        this.contractCode = contractCode;
        this.clOrdId = clOrdId;
        this.price = price;
        this.volume = volume;
        this.direction = direction;
        this.offset = offset;
        this.lever_rate = lever_rate;
        this.orderPriceType = orderPriceType;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getContract_type() {
        return contract_type;
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

    public Integer getLever_rate() {
        return lever_rate;
    }

    public String getOrderPriceType() {
        return orderPriceType;
    }
}
