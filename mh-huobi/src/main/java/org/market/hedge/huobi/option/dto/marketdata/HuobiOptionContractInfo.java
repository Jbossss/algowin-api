package org.market.hedge.huobi.option.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class HuobiOptionContractInfo {

    String symbol;	//true		品种代码	"BTC","ETH"...
    String trade_partition;	//	true		交易分区	"USDT"
    String contract_code;	//	true		合约代码 （若行权价存在小数点，则用”+“表示小数点，如BTC当周看涨期权行权价为9002.35，则合约代码为BTC-USDT-200508-C-9002+35）	"BTC-USDT-200508-C-8800"
    String contract_type;	//	true		合约类型	当周:"this_week", 次周:"next_week", 季度:"quarter"
    BigDecimal contract_size;	//	true		合约面值，即1张合约对应多少标的币	0.01...
    BigDecimal price_tick;	//	true		合约价格最小变动精度	0.001, 0.01...
    String delivery_date;	//	true		合约交割日期	如"20200626"
    String create_date;	//	true		合约上市日期	如"20200515"
    Integer contract_status;	//	true		合约状态	0:下市 1:上市 2:待上市 3:停牌 4:暂停上市中 5:结算中 6:交割中 7 结算完成 8 交割完成 9:暂停交易中
    String option_right_type;	//	true		期权行权类型	C:看涨期权 P:看跌期权
    BigDecimal exercise_price;	//	true		行权价	如6622
    String delivery_asset;	//	true		交割币种	如"BTC"

    public HuobiOptionContractInfo(
                              @JsonProperty("symbol") String symbol,
                              @JsonProperty("trade_partition") String trade_partition,
                              @JsonProperty("contract_code") String contract_code,
                              @JsonProperty("contract_type") String contract_type,
                              @JsonProperty("contract_size") BigDecimal contract_size,
                              @JsonProperty("price_tick") BigDecimal price_tick,
                              @JsonProperty("delivery_date") String delivery_date,
                              @JsonProperty("create_date") String create_date,
                              @JsonProperty("contract_status") Integer contract_status,
                              @JsonProperty("option_right_type") String option_right_type,
                              @JsonProperty("exercise_price") BigDecimal exercise_price,
                              @JsonProperty("delivery_asset") String delivery_asset,
                              @JsonProperty("quote_asset") String quote_asset) {
        this.symbol = symbol;
        this.trade_partition = trade_partition;
        this.contract_code = contract_code;
        this.contract_type = contract_type;
        this.contract_size = contract_size;
        this.price_tick = price_tick;
        this.delivery_date = delivery_date;
        this.create_date = create_date;
        this.contract_status = contract_status;
        this.option_right_type = option_right_type;
        this.exercise_price = exercise_price;
        this.delivery_asset = delivery_asset;
        this.quote_asset = quote_asset;
    }

    String quote_asset;	//	true		报价币种	如"USDT"

    public String getSymbol() {
        return symbol;
    }

    public String getTrade_partition() {
        return trade_partition;
    }

    public String getContract_code() {
        return contract_code;
    }

    @Override
    public String toString() {
        return "OptionContractInfo{" +
                "symbol='" + symbol + '\'' +
                ", trade_partition='" + trade_partition + '\'' +
                ", contract_code='" + contract_code + '\'' +
                ", contract_type='" + contract_type + '\'' +
                ", contract_size=" + contract_size +
                ", price_tick=" + price_tick +
                ", delivery_date='" + delivery_date + '\'' +
                ", create_date='" + create_date + '\'' +
                ", contract_status=" + contract_status +
                ", option_right_type='" + option_right_type + '\'' +
                ", exercise_price=" + exercise_price +
                ", delivery_asset='" + delivery_asset + '\'' +
                ", quote_asset='" + quote_asset + '\'' +
                '}';
    }

    public String getContract_type() {
        return contract_type;
    }

    public BigDecimal getContract_size() {
        return contract_size;
    }

    public BigDecimal getPrice_tick() {
        return price_tick;
    }

    public String getDelivery_date() {
        return delivery_date;
    }

    public String getCreate_date() {
        return create_date;
    }

    public Integer getContract_status() {
        return contract_status;
    }

    public String getOption_right_type() {
        return option_right_type;
    }

    public BigDecimal getExercise_price() {
        return exercise_price;
    }

    public String getDelivery_asset() {
        return delivery_asset;
    }

    public String getQuote_asset() {
        return quote_asset;
    }
}
