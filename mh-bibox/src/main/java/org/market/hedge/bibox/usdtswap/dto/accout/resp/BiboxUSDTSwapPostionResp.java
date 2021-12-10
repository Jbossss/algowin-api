package org.market.hedge.bibox.usdtswap.dto.accout.resp;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class BiboxUSDTSwapPostionResp {
    /**
     * 0代表成功，否则代表失败
     */
    Integer state;
    /**
     *	杠杆
     */
    Integer l;
    /**
     *	仓位方向: 1, 多仓 2 , 空仓
     */
    Integer sd;
    /**
     * 告警价格
     */
    BigDecimal pa;
    /**
     * 用户id
     */
    Integer ui;
    /**
     * 	爆仓价格
     */
    BigDecimal pf;
    /**
     *	仓位模式: 1全仓, 2逐仓
     */
    Integer md;
    /**
     *	可平仓位价值
     */
    BigDecimal lc;
    /**
     *	交易对
     */
    String pi;
    /**
     *	保证金
     */
    BigDecimal mg;
    /**
     *	仓位价值 = 合约张数X合约面值
     */
    BigDecimal hc;
    /**
     * 开仓价格
     */
    BigDecimal po;

    public BiboxUSDTSwapPostionResp(
            @JsonProperty("state") Integer state,
            @JsonProperty("l") Integer l,
            @JsonProperty("sd") Integer sd,
            @JsonProperty("pa") BigDecimal pa,
            @JsonProperty("ui") Integer ui,
            @JsonProperty("pf") BigDecimal pf,
            @JsonProperty("md") Integer md,
            @JsonProperty("lc") BigDecimal lc,
            @JsonProperty("pi") String pi,
            @JsonProperty("mg") BigDecimal mg,
            @JsonProperty("hc") BigDecimal hc,
            @JsonProperty("po") BigDecimal po) {
        this.state = state;
        this.l = l;
        this.sd = sd;
        this.pa = pa;
        this.ui = ui;
        this.pf = pf;
        this.md = md;
        this.lc = lc;
        this.pi = pi;
        this.mg = mg;
        this.hc = hc;
        this.po = po;
    }


    public Integer getState() {
        return state;
    }

    public Integer getL() {
        return l;
    }

    public Integer getSd() {
        return sd;
    }

    public BigDecimal getPa() {
        return pa;
    }

    public Integer getUi() {
        return ui;
    }

    public BigDecimal getPf() {
        return pf;
    }

    public Integer getMd() {
        return md;
    }

    public BigDecimal getLc() {
        return lc;
    }

    public String getPi() {
        return pi;
    }

    public BigDecimal getMg() {
        return mg;
    }

    public BigDecimal getHc() {
        return hc;
    }

    public BigDecimal getPo() {
        return po;
    }
}
