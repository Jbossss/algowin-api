package org.market.hedge.huobi.futures.dto.account;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HuobiFuturesPositionInfoRequest {

    @JsonProperty("symbol")
    String symbol;	//false		品种代码		支持大小写,""BTC","ETH"...如果缺省，默认返回所有品种

    public HuobiFuturesPositionInfoRequest(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
