package org.market.hedge.huobi.futures.dto.account;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class HuobiFuturesPosition {

    String symbol;	//true		品种代码	"BTC","ETH"...
    String contract_code;	//	true		合约代码	"BTC180914" ...
    String contract_type;	//	true		合约类型	当周:"this_week", 次周:"next_week", 当季:"quarter", 次季:"next_quarter"
    BigDecimal volume	;	//true		持仓量
    BigDecimal available;	//	true		可平仓数量
    BigDecimal frozen	;	//true		冻结数量
    BigDecimal cost_open;	//	true		开仓均价
    BigDecimal cost_hold;	//	true		持仓均价
    BigDecimal profit_unreal;	//	true		未实现盈亏
    BigDecimal profit_rate;	//	true		收益率
    BigDecimal profit	;	//true		收益
    BigDecimal position_margin;	//	true		持仓保证金
    Integer lever_rate;	//	true		杠杠倍数
    String direction	;	//true		"buy":买 "sell":卖
    BigDecimal last_price;	//	true		最新价


    public HuobiFuturesPosition(
            @JsonProperty("symbol") String symbol,
            @JsonProperty("contract_code")  String contract_code,
            @JsonProperty("contract_type")  String contract_type,
            @JsonProperty("volume")  BigDecimal volume,
            @JsonProperty("available")  BigDecimal available,
            @JsonProperty("frozen")  BigDecimal frozen,
            @JsonProperty("cost_open")  BigDecimal cost_open,
            @JsonProperty("cost_hold")  BigDecimal cost_hold,
            @JsonProperty("profit_unreal")  BigDecimal profit_unreal,
            @JsonProperty("profit_rate")  BigDecimal profit_rate,
            @JsonProperty("profit")  BigDecimal profit,
            @JsonProperty("position_margin")  BigDecimal position_margin,
            @JsonProperty("lever_rate")  Integer lever_rate,
            @JsonProperty("direction")  String direction,
            @JsonProperty("last_price")  BigDecimal last_price) {
        this.symbol = symbol;
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
        this.position_margin = position_margin;
        this.lever_rate = lever_rate;
        this.direction = direction;
        this.last_price = last_price;
    }

    public String getSymbol() {
        return symbol;
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

    public BigDecimal getPosition_margin() {
        return position_margin;
    }

    public Integer getLever_rate() {
        return lever_rate;
    }

    public String getDirection() {
        return direction;
    }

    public BigDecimal getLast_price() {
        return last_price;
    }
}
