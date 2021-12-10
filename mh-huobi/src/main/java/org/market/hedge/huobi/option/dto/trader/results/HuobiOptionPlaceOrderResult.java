package org.market.hedge.huobi.option.dto.trader.results;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.market.hedge.huobi.option.dto.HuobiOptionResult;

public class HuobiOptionPlaceOrderResult extends HuobiOptionResult<HuobiOptionOrderResult> {

    public HuobiOptionPlaceOrderResult(@JsonProperty("status")String status,
                                       @JsonProperty("ts")Long ts,
                                       @JsonProperty("errCode")String errCode,
                                       @JsonProperty("errMsg")String errMsg,
                                       @JsonProperty("data") HuobiOptionOrderResult result) {
        super(status, ts, errCode, errMsg, result);
    }

}
