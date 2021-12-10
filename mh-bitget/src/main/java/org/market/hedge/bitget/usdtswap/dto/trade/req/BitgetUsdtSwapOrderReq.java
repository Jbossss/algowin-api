package org.market.hedge.bitget.usdtswap.dto.trade.req;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * symbol
 * orderDataList	（price=5,size=2,type=1,match_price=1,order_type=1,client_oid="abc")
 */
public class BitgetUsdtSwapOrderReq {
    @JsonProperty("symbol")
    protected  String symbol;
    @JsonProperty("orderDataList")
    protected List<BitgetUsdtSwapOrderDataListReq> orderDataList;

    public BitgetUsdtSwapOrderReq(String symbol, List<BitgetUsdtSwapOrderDataListReq> orderDataList) {
        this.symbol = symbol;
        this.orderDataList = orderDataList;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setOrderDataList(List<BitgetUsdtSwapOrderDataListReq> orderDataList) {
        this.orderDataList = orderDataList;
    }

    public String getSymbol() {
        return symbol;
    }

    public List<BitgetUsdtSwapOrderDataListReq> getOrderDataList() {
        return orderDataList;
    }
}
