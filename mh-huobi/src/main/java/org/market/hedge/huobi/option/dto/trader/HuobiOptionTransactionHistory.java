package org.market.hedge.huobi.option.dto.trader;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class HuobiOptionTransactionHistory {

    String id;	//true		全局唯一的交易标识
    long match_id;	//	true		撮合结果id, 与订单ws推送orders.$symbol-$partition推送结果中的trade_id是相同的，非唯一，可重复，注意：一个撮合结果代表一个taker单和N个maker单的成交记录的集合，如果一个taker单吃了N个maker单，那这N笔trade都是一样的撮合结果id
    long order_id;	//	true		订单ID
    String order_id_str;	//	true		String格式的订单ID
    String symbol;	//	true		品种代码	"BTC","ETH"...
    String trade_partition;	//	true		交易分区	"USDT"
    String contract_type;	//	true		合约类型	当周:"this_week", 次周:"next_week", 季度:"quarter"
    String contract_code;	//	true		合约代码	"BTC-USDT-200508-C-8800"
    String direction;	//	true		买卖方向	"buy":买 "sell":卖
    String offset;	//	true		开平方向	"open":开 "close":平
    BigDecimal trade_volume;	//	true		成交数量（张）
    BigDecimal trade_price;	//	true		成交价格
    BigDecimal trade_turnover;	//	true		成交金额
    long create_date;	//	true		成交时间
    BigDecimal offset_profitloss;	//	true		平仓盈亏
    BigDecimal trade_fee;	//	true		成交手续费
    String fee_asset;	//	true		手续费币种
    String role;	//	true		成交类型	"taker"：吃单；"maker"：挂单；"Otm delivery records"：虚值行权；"Itm delivery records"：实值行权
    String order_source;	//	true		订单来源
    String delivery_date;	//	true		到期日	如"20200508"
    String  option_right_type;	//	true		期权行权类型	C:看涨期权 P:看跌期权
    BigDecimal exercise_price;	//	true		行权价
    String quote_asset;	//	true		报价币种	如"USDT"

    public HuobiOptionTransactionHistory(
            @JsonProperty("id") String id,
            @JsonProperty("match_id") long match_id,
            @JsonProperty("order_id") long order_id,
            @JsonProperty("order_id_str") String order_id_str,
            @JsonProperty("symbol") String symbol,
            @JsonProperty("trade_partition") String trade_partition,
            @JsonProperty("contract_type") String contract_type,
            @JsonProperty("contract_code") String contract_code,
            @JsonProperty("direction") String direction,
            @JsonProperty("offset") String offset,
            @JsonProperty("trade_volume") BigDecimal trade_volume,
            @JsonProperty("trade_price") BigDecimal trade_price,
            @JsonProperty("trade_turnover") BigDecimal trade_turnover,
            @JsonProperty("create_date") long create_date,
            @JsonProperty("offset_profitloss") BigDecimal offset_profitloss,
            @JsonProperty("trade_fee") BigDecimal trade_fee,
            @JsonProperty("fee_asset") String fee_asset,
            @JsonProperty("role") String role,
            @JsonProperty("order_source") String order_source,
            @JsonProperty("delivery_date") String delivery_date,
            @JsonProperty("option_right_type") String option_right_type,
            @JsonProperty("exercise_price") BigDecimal exercise_price,
            @JsonProperty("quote_asset") String quote_asset) {
        this.id = id;
        this.match_id = match_id;
        this.order_id = order_id;
        this.order_id_str = order_id_str;
        this.symbol = symbol;
        this.trade_partition = trade_partition;
        this.contract_type = contract_type;
        this.contract_code = contract_code;
        this.direction = direction;
        this.offset = offset;
        this.trade_volume = trade_volume;
        this.trade_price = trade_price;
        this.trade_turnover = trade_turnover;
        this.create_date = create_date;
        this.offset_profitloss = offset_profitloss;
        this.trade_fee = trade_fee;
        this.fee_asset = fee_asset;
        this.role = role;
        this.order_source = order_source;
        this.delivery_date = delivery_date;
        this.option_right_type = option_right_type;
        this.exercise_price = exercise_price;
        this.quote_asset = quote_asset;
    }

    public String getId() {
        return id;
    }

    public long getMatch_id() {
        return match_id;
    }

    public long getOrder_id() {
        return order_id;
    }

    public String getOrder_id_str() {
        return order_id_str;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getTrade_partition() {
        return trade_partition;
    }

    public String getContract_type() {
        return contract_type;
    }

    public String getContract_code() {
        return contract_code;
    }

    public String getDirection() {
        return direction;
    }

    public String getOffset() {
        return offset;
    }

    public BigDecimal getTrade_volume() {
        return trade_volume;
    }

    public BigDecimal getTrade_price() {
        return trade_price;
    }

    public BigDecimal getTrade_turnover() {
        return trade_turnover;
    }

    public long getCreate_date() {
        return create_date;
    }

    public BigDecimal getOffset_profitloss() {
        return offset_profitloss;
    }

    public BigDecimal getTrade_fee() {
        return trade_fee;
    }

    public String getFee_asset() {
        return fee_asset;
    }

    public String getRole() {
        return role;
    }

    public String getOrder_source() {
        return order_source;
    }

    public String getDelivery_date() {
        return delivery_date;
    }

    public String getOption_right_type() {
        return option_right_type;
    }

    public BigDecimal getExercise_price() {
        return exercise_price;
    }

    public String getQuote_asset() {
        return quote_asset;
    }

    @Override
    public String toString() {
        return "HuobiOptionTransactionHistory{" +
                "id='" + id + '\'' +
                ", match_id=" + match_id +
                ", order_id=" + order_id +
                ", order_id_str='" + order_id_str + '\'' +
                ", symbol='" + symbol + '\'' +
                ", trade_partition='" + trade_partition + '\'' +
                ", contract_type='" + contract_type + '\'' +
                ", contract_code='" + contract_code + '\'' +
                ", direction='" + direction + '\'' +
                ", offset='" + offset + '\'' +
                ", trade_volume=" + trade_volume +
                ", trade_price=" + trade_price +
                ", trade_turnover=" + trade_turnover +
                ", create_date=" + create_date +
                ", offset_profitloss=" + offset_profitloss +
                ", trade_fee=" + trade_fee +
                ", fee_asset='" + fee_asset + '\'' +
                ", role='" + role + '\'' +
                ", order_source='" + order_source + '\'' +
                ", delivery_date='" + delivery_date + '\'' +
                ", option_right_type='" + option_right_type + '\'' +
                ", exercise_price=" + exercise_price +
                ", quote_asset='" + quote_asset + '\'' +
                '}';
    }
}
