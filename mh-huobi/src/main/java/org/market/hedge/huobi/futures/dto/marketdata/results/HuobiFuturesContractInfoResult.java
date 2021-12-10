package org.market.hedge.huobi.futures.dto.marketdata.results;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.market.hedge.huobi.futures.dto.marketdata.HuobiFuturesContractInfo;
import org.market.hedge.huobi.option.dto.HuobiOptionResult;

import java.util.List;

public class HuobiFuturesContractInfoResult  extends HuobiOptionResult<List<HuobiFuturesContractInfo>> {

    public HuobiFuturesContractInfoResult(
            @JsonProperty("status") String status,
            @JsonProperty("ts")  Long ts,
            @JsonProperty("errCode") String errCode,
            @JsonProperty("errMsg") String errMsg,
            @JsonProperty("data") List<HuobiFuturesContractInfo> data) {
        super(status, ts, errCode, errMsg, data);
    }
}
