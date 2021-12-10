package org.market.hedge.huobi.option.dto.trader.results;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class HuobiOptionPlaceOrdersDataResult {

    @JsonProperty("errors")
    public List<HuobiOptionErrrorOrderResult> errors;


    @JsonProperty("success")
    public List<HuobiOptionOrderResult> success;

    public HuobiOptionPlaceOrdersDataResult(@JsonProperty("errors")List<HuobiOptionErrrorOrderResult> errors,@JsonProperty("success")List<HuobiOptionOrderResult> success){
        this.errors=errors;
        this.success=success;
    }

    public List<HuobiOptionErrrorOrderResult> getErrors() {
        return errors;
    }

    public List<HuobiOptionOrderResult> getSuccess() {
        return success;
    }
}
