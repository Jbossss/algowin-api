package org.market.hedge.okex.dto.marketdata.resp;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.market.hedge.okex.dto.OkexResult;

import java.util.List;

public class OkexDepthResp extends OkexResult<List<OkexDepth>> {

    public OkexDepthResp(
            @JsonProperty("code") String code,
            @JsonProperty("msg") String msg,
            @JsonProperty("data") List<OkexDepth> data) {
        super(code, msg, data);
    }

}
