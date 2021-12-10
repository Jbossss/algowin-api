package org.market.hedge.huobi.option.dto.trader.results;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.market.hedge.huobi.option.dto.HuobiOptionResult;

public class HuobiOptionCancelAllResult extends HuobiOptionResult<HuobiOptionPlaceOrdersDataResult> {

    public HuobiOptionCancelAllResult(@JsonProperty("status")String status,
                                 @JsonProperty("ts")Long ts,
                                 @JsonProperty("err_code")String errCode,
                                 @JsonProperty("err_msg")String errMsg,
                                 @JsonProperty("data")HuobiOptionPlaceOrdersDataResult data) {
        super(status, ts, errCode, errMsg, data);
    }

}
