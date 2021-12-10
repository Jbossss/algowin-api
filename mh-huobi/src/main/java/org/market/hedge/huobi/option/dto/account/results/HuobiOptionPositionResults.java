package org.market.hedge.huobi.option.dto.account.results;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class HuobiOptionPositionResults {

    private final String symbol;	//品种代码	"BTC","ETH"...
    private final String trade_partition;	//交易分区	"USDT"
    private final String contract_code;	//合约代码	"BTC-USDT-200508-C-8800" ...
    private final String contract_type;	//合约类型	当周:"this_week", 次周:"next_week", 季度:"quarter"
    private final BigDecimal volume;	//持仓量
    private final BigDecimal  available;	//可平仓数量
    private final BigDecimal  frozen;	//冻结数量
    private final BigDecimal cost_open;	//开仓均价
    private final BigDecimal cost_hold;	//	持仓均价
    private final BigDecimal profit_unreal;	//未实现盈亏

    public String getSymbol() {
        return symbol;
    }

    public String getTrade_partition() {
        return trade_partition;
    }

    public String getContract_code() {
        return contract_code;
    }

    public String getContract_type() {
        return contract_type;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public BigDecimal getAvailable() {
        return available;
    }

    public BigDecimal getFrozen() {
        return frozen;
    }

    public BigDecimal getCost_open() {
        return cost_open;
    }

    public BigDecimal getCost_hold() {
        return cost_hold;
    }

    public BigDecimal getProfit_unreal() {
        return profit_unreal;
    }

    public BigDecimal getProfit_rate() {
        return profit_rate;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public BigDecimal getMargin_position() {
        return margin_position;
    }

    public BigDecimal getPosition_value() {
        return position_value;
    }

    public String getDirection() {
        return direction;
    }

    public BigDecimal getLast_price() {
        return last_price;
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

    public String getMargin_asset() {
        return margin_asset;
    }

    private final BigDecimal profit_rate;	//收益率
    private final BigDecimal profit;	//收益
    private final BigDecimal margin_position;	//履约保证金
    private final BigDecimal position_value;	//仓位价值
    private final String direction;	//"buy":买 "sell":卖
    private final BigDecimal last_price;	//最新价
    private final String delivery_date;	//到期日	如"20200508"
    private final String option_right_type;	//期权行权类型	C:看涨期权 P:看跌期权
    private final BigDecimal exercise_price;	//行权价
    private final String quote_asset;	//报单币种	"USDT"...
    private final String margin_asset;	//保证金币种	"BTC"，”ETH“，”USDT“


    public HuobiOptionPositionResults(@JsonProperty("symbol")String symbol,
                                      @JsonProperty("trade_partition")String trade_partition,
                                      @JsonProperty("contract_code")String contract_code,
                                      @JsonProperty("contract_type")String contract_type,
                                      @JsonProperty("volume")BigDecimal volume,
                                      @JsonProperty("available")BigDecimal available,
                                      @JsonProperty("frozen")BigDecimal frozen,
                                      @JsonProperty("cost_open")BigDecimal cost_open,
                                      @JsonProperty("cost_hold")BigDecimal cost_hold,
                                      @JsonProperty("profit_unreal")BigDecimal profit_unreal,
                                      @JsonProperty("profit_rate")BigDecimal profit_rate,
                                      @JsonProperty("profit")BigDecimal profit,
                                      @JsonProperty("margin_position")BigDecimal margin_position,
                                      @JsonProperty("position_value")BigDecimal position_value,
                                      @JsonProperty("direction")String direction,
                                      @JsonProperty("last_price")BigDecimal last_price,
                                      @JsonProperty("delivery_date")String delivery_date,
                                      @JsonProperty("option_right_type")String option_right_type,
                                      @JsonProperty("exercise_price")BigDecimal exercise_price,
                                      @JsonProperty("quote_asset")String quote_asset,
                                      @JsonProperty("margin_asset")String margin_asset) {
        this.symbol = symbol;
        this.trade_partition = trade_partition;
        this.contract_code = contract_code;
        this.contract_type = contract_type;
        this.volume = volume;
        this.available = available;
        this.frozen = frozen;
        this.cost_open = cost_open;
        this.cost_hold = cost_hold;
        this.profit_unreal = profit_unreal;
        this.profit_rate = profit_rate;
        this.profit = profit;
        this.margin_position = margin_position;
        this.position_value = position_value;
        this.direction = direction;
        this.last_price = last_price;
        this.delivery_date = delivery_date;
        this.option_right_type = option_right_type;
        this.exercise_price = exercise_price;
        this.quote_asset = quote_asset;
        this.margin_asset = margin_asset;
    }
}
