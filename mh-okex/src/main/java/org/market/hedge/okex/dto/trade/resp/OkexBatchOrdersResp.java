package org.market.hedge.okex.dto.trade.resp;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.market.hedge.okex.dto.OkexResult;

import java.util.List;

public class OkexBatchOrdersResp extends OkexResult<List<OkexOrder>> {


    public OkexBatchOrdersResp (
            @JsonProperty("code") String code,
            @JsonProperty("msg") String msg,
            @JsonProperty("data") List<OkexOrder> data) {
        super(code, msg, data);
    }
}
