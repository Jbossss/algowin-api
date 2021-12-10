package org.market.hedge.huobi.option.dto.trader.results;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HuobiOptionErrrorOrderResult {
    @JsonProperty("index")
    private final Integer index;  //订单索引
    @JsonProperty("err_code")
    private final Integer err_code;  //错误码
    @JsonProperty("err_msg")
    private final String err_msg;  //错误信息

    public Integer getIndex() {
        return index;
    }

    public Integer getErr_code() {
        return err_code;
    }

    public String getErr_msg() {
        return err_msg;
    }

    public HuobiOptionErrrorOrderResult(Integer index, Integer err_code, String err_msg) {
        this.index = index;
        this.err_code = err_code;
        this.err_msg = err_msg;
    }



}
