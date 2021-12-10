package org.market.hedge.huobi.option.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HuobiOptionContractCodeRequest {

    @JsonProperty("symbol")
    private String symbol;

    public String getSymbol() {
        return symbol;
    }

    public String getTradePartition() {
        return tradePartition;
    }

    public String getContractCode() {
        return contractCode;
    }

    @JsonProperty("trade_partition")
    private String tradePartition;

    @JsonProperty("contract_code")
    private String contractCode;

    public HuobiOptionContractCodeRequest(
            String symbol,
            String tradePartition,
            String contractCode
    ){
        this.symbol = symbol;
        this.tradePartition = tradePartition;
        this.contractCode = contractCode;
    }

}
