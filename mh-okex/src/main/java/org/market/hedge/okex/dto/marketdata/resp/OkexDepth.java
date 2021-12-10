package org.market.hedge.okex.dto.marketdata.resp;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;

public class OkexDepth {

    /**
     *  asks	Array	卖方深度
     */
    @JsonProperty("asks")
    List<List<BigDecimal>> asks;

    /**
     *  bids	Array	买方深度
     */
    @JsonProperty("bids")
    List<List<BigDecimal>> bids;

    /**
     * ts	String	深度产生的时间
     */
    @JsonProperty("ts")
    String ts;

    /*public OkexDepth(
            List<List<BigDecimal>> asks,
            List<List<BigDecimal>> bids,
            String ts) {
        this.asks=asks;
        this.bids=bids;
        this.ts=ts;
    }*/

    public List<List<BigDecimal>> getAsks() {
        return asks;
    }

    public List<List<BigDecimal>> getBids() {
        return bids;
    }

    public String getTs() {
        return ts;
    }
}
