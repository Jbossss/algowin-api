package org.market.hedge.huobi.option.dto.account.results;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.market.hedge.huobi.option.dto.HuobiOptionResult;

import java.util.List;

public class HuobiOptionPositionsResults extends HuobiOptionResult<List<HuobiOptionPositionResults>> {

    public HuobiOptionPositionsResults(@JsonProperty("status")String status,
                                       @JsonProperty("ts")Long ts,
                                       @JsonProperty("errCode")String errCode,
                                       @JsonProperty("errMsg")String errMsg,
                                       @JsonProperty("data")List<HuobiOptionPositionResults> result) {
        super(status, ts, errCode, errMsg, result);
    }

}
