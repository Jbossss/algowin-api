package org.market.hedge.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Result {

    String code;
    String msg;

    public Result(@JsonProperty("code") String code, @JsonProperty("msg") String msg) {
        this.code = code;
        this.msg = msg;
    }
}
