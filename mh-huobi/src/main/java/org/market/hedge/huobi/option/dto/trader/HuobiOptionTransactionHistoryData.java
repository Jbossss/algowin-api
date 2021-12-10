package org.market.hedge.huobi.option.dto.trader;


import com.fasterxml.jackson.annotation.JsonProperty;

public class HuobiOptionTransactionHistoryData {

    @JsonProperty("trades")
    HuobiOptionTransactionHistory[] trades;

    int current_page;	//true		当前页

    int total_page;	//	true		总页数

    int total_size;	//	true		总条数

    public HuobiOptionTransactionHistoryData(
            @JsonProperty("trades") HuobiOptionTransactionHistory[] trades,
            @JsonProperty("current_page") int current_page,
            @JsonProperty("total_page") int total_page,
            @JsonProperty("total_size") int total_size) {
        this.trades = trades;
        this.current_page = current_page;
        this.total_page = total_page;
        this.total_size = total_size;
    }

    public HuobiOptionTransactionHistory[] getTrades() {
        return trades;
    }

    public int getCurrent_page() {
        return current_page;
    }

    public int getTotal_page() {
        return total_page;
    }

    public int getTotal_size() {
        return total_size;
    }

}
