package org.market.hedge.core;

import java.math.BigDecimal;

public class PositionInfo {

    /**
     * //true		品种代码	"BTC","ETH"...
     * */
    private String symbol;

    /**
     *	true		合约代码	"BTC180914" ...
     * */
    private String contract_code;

    /**
     * true		持仓量
     * */
    private BigDecimal volume	;

    /**
     *	true		可平仓数量
     * */
    private BigDecimal available;

    /**
     * true		冻结数量
     * */
    private BigDecimal frozen	;

    /**
     * true   开仓均价
     * */
    private BigDecimal cost_open;
    /**
     *	true		持仓均价
     * */
    private BigDecimal cost_hold;

    /**
     * 	true		未实现盈亏
     * */
    private BigDecimal profit_unreal;

    /**
     *	true		收益率
     * */
    private BigDecimal profit_rate;

    /**
     * true		收益
     * */
    private BigDecimal profit	;

    /**
     * true   持仓保证金
     * */
    private BigDecimal position_margin;
    /**
     * true  杠杠倍数
     * */
    private Integer lever_rate;

    /**
     * true		"buy":买 "sell":卖
     * */
    private String direction;



    public static Builder builder() {
        return new Builder();
    }

    public PositionInfo(Builder builder) {
        this. symbol = builder.symbol;
        this. contract_code = builder.contract_code;
        this. volume	 = builder.volume;
        this. available = builder.available;
        this. frozen	 = builder.frozen;
        this. cost_open = builder.cost_open;
        this. cost_hold = builder.cost_hold;
        this. profit_unreal = builder.profit_unreal;
        this. profit_rate = builder.profit_rate;
        this. profit	 = builder.profit;
        this. position_margin = builder.position_margin;
        this. lever_rate= builder.lever_rate;
        this. direction	 = builder.direction;
    }

    public static class Builder {
        private String symbol = null;
        private String contract_code = null;
        private BigDecimal volume	 = null;
        private BigDecimal available = null;
        private BigDecimal frozen	 = null;
        private BigDecimal cost_open = null;
        private BigDecimal cost_hold = null;
        private  BigDecimal profit_unreal = null;
        private  BigDecimal profit_rate = null;
        private  BigDecimal profit	 = null;
        private  BigDecimal position_margin = null;
        private  Integer lever_rate= 0;
        private String direction = null;

        public Builder addSymbol(String symbol) {
            this.symbol = symbol;
            return this;
        }

        public Builder addContract_code(String contract_code) {
            this.contract_code = contract_code;
            return this;
        }

        public Builder addVolume(BigDecimal volume) {
            this.volume = volume;
            return this;
        }

        public Builder addAvailable(BigDecimal available) {
            this.available = available;
            return this;
        }

        public Builder addFrozen(BigDecimal frozen) {
            this.frozen = frozen;
            return this;
        }

        public Builder addCost_open(BigDecimal cost_open) {
            this.cost_open = cost_open;
            return this;
        }

        public Builder addCost_hold(BigDecimal cost_hold) {
            this.cost_hold = cost_hold;
            return this;
        }

        public Builder addProfit_unreal(BigDecimal profit_unreal) {
            this.profit_unreal = profit_unreal;
            return this;
        }

        public Builder addProfit_rate(BigDecimal profit_rate) {
            this.profit_rate = profit_rate;
            return this;
        }

        public Builder addProfit(BigDecimal profit) {
            this.profit = profit;
            return this;
        }

        public Builder addPosition_margin(BigDecimal position_margin) {
            this.position_margin = position_margin;
            return this;
        }

        public Builder addLever_rate(Integer lever_rate) {
            this.lever_rate = lever_rate;
            return this;
        }

        public Builder addDirection(String direction) {
            this.direction = direction;
            return this;
        }

        public PositionInfo build() {
            return new PositionInfo(this);
        }
    }


    public String getSymbol() {
        return symbol;
    }

    public String getContract_code() {
        return contract_code;
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

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setContract_code(String contract_code) {
        this.contract_code = contract_code;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    public void setAvailable(BigDecimal available) {
        this.available = available;
    }

    public void setFrozen(BigDecimal frozen) {
        this.frozen = frozen;
    }

    public void setCost_open(BigDecimal cost_open) {
        this.cost_open = cost_open;
    }

    public void setCost_hold(BigDecimal cost_hold) {
        this.cost_hold = cost_hold;
    }

    public void setProfit_unreal(BigDecimal profit_unreal) {
        this.profit_unreal = profit_unreal;
    }

    public void setProfit_rate(BigDecimal profit_rate) {
        this.profit_rate = profit_rate;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    public void setPosition_margin(BigDecimal position_margin) {
        this.position_margin = position_margin;
    }

    public void setLever_rate(Integer lever_rate) {
        this.lever_rate = lever_rate;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }


}
