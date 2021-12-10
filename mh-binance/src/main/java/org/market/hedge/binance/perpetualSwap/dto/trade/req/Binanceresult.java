package org.market.hedge.binance.perpetualSwap.dto.trade.req;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Binanceresult {

    String code;
    String msg;

    public Binanceresult(@JsonProperty("code") String code, @JsonProperty("msg") String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
