package org.market.hedge.huobi.option.dto.trader;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HuobiOptionTransactionHistoryRequest {

    @JsonProperty("symbol")
    String symbol;	//true		品种代码	"BTC","ETH"，如果缺省，默认返回所有品种
    @JsonProperty("trade_partition")
    String trade_partition;	//	false		交易分区，不填默认”USDT“	"USDT"
    @JsonProperty("trade_type")
    Integer trade_type;	//	true		交易类型	0:全部,1:买入开多,2: 卖出开空,3: 买入平空,4: 卖出平多
    @JsonProperty("create_date")
    Integer create_date;	//	true		日期	可随意输入正整数，如果参数超过90则默认查询90天的数据
    @JsonProperty("contract_code")
    String contract_code;	//	false		合约代码	BTC-USDT-200508-C-8800
    @JsonProperty("page_index")
    Integer page_index;	//	false		页码，不填默认第1页
    @JsonProperty("page_size")
    Integer page_size;	//	false		不填默认20，不得多于50

    public HuobiOptionTransactionHistoryRequest(String symbol, String trade_partition, Integer trade_type, Integer create_date, String contract_code, Integer page_index, Integer page_size) {
        this.symbol = symbol;
        this.trade_partition = trade_partition;
        this.trade_type = trade_type;
        this.create_date = create_date;
        this.contract_code = contract_code;
        this.page_index = page_index;
        this.page_size = page_size;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getTrade_partition() {
        return trade_partition;
    }

    public Integer getTrade_type() {
        return trade_type;
    }

    public Integer getCreate_date() {
        return create_date;
    }

    public String getContract_code() {
        return contract_code;
    }

    public Integer getPage_index() {
        return page_index;
    }

    public Integer getPage_size() {
        return page_size;
    }
}
