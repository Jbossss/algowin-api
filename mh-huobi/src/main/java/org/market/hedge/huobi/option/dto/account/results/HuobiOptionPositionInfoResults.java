package org.market.hedge.huobi.option.dto.account.results;

import org.market.hedge.huobi.option.dto.HuobiOptionResult;

import java.util.List;

public class HuobiOptionPositionInfoResults extends HuobiOptionResult<List<HuobiOptionPositionResults>> {

    public HuobiOptionPositionInfoResults(String status, Long ts, String errCode, String errMsg, List<HuobiOptionPositionResults> data) {
        super(status, ts, errCode, errMsg, data);
    }
}
