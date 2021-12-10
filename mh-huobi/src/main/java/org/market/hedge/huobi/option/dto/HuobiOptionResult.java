package org.market.hedge.huobi.option.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class HuobiOptionResult<V> {


    private final String status;
    private final Long ts;
    private final String errCode;
    private final String errMsg;
    private final V data;

    @JsonCreator
    public HuobiOptionResult(
            @JsonProperty("status") String status,
            @JsonProperty("ts") Long ts,
            @JsonProperty("err-code") String errCode,
            @JsonProperty("err-msg") String errMsg,
            V data) {
        this.status = status;
        this.ts = ts;
        this.errCode = errCode;
        this.errMsg = errMsg;
        this.data = data;
    }

    public Long getTs() {
        return ts;
    }

    public boolean isSuccess() {
        return getStatus().equals("ok");
    }

    public String getStatus() {
        return status;
    }

    public String getError() {
        if (Objects.isNull(errMsg)) return "null";
        return (errMsg.length() == 0) ? errCode : errMsg;
    }

    public V getData() {
        return data;
    }

    @Override
    public String toString() {
        return String.format(
                "HuobiResult [%s, %s]", status, isSuccess() ? getData().toString() : getError());
    }

}
