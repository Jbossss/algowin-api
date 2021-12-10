package org.market.hedge.bitget.usdtswap.dto.marketdata.resp;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

/**
 * @author chuxianbo
 */
public class BitgeUSwapDepthResult {

    List<List<String>> asks;

    List<List<String>> bids;

    Date timestamp;


    public BitgeUSwapDepthResult(
            @JsonProperty("asks") List<List<String>> asks,
            @JsonProperty("bids")  List<List<String>> bids,
            @JsonProperty("timestamp")  Date timestamp) {
        this.asks = asks;
        this.bids = bids;
        this.timestamp = timestamp;
    }

    public List<List<String>> getAsks() {
        return asks;
    }

    public List<List<String>> getBids() {
        return bids;
    }

    public Date getTimestamp() {
        return timestamp;
    }

}
