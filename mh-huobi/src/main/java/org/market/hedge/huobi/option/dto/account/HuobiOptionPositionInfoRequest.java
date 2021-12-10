package org.market.hedge.huobi.option.dto.account;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HuobiOptionPositionInfoRequest {

    @JsonProperty("symbol")
    String symbol;	//false		品种代码	"BTC","ETH"，如果缺省，默认返回所有品种

    @JsonProperty("trade_partition")
    String trade_partition;	//	false		交易分区，不填默认”USDT“	"USDT"

    public String getSymbol() {
        return symbol;
    }

    public String getTrade_partition() {
        return trade_partition;
    }

    public HuobiOptionPositionInfoRequest(String symbol, String trade_partition) {
        this.symbol = symbol;
        this.trade_partition = trade_partition;
    }

}
