package org.market.hedge.bibox.coinswap.dto.account.resp;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class BiboxCoinSwapPostionResp {

    /**
     * 杠杆
     */
    public final  Integer l;

    /**
     * 仓位方向: 1, 多仓 2 , 空仓
     */
    public final  Integer sd;

    /**
     * 告警价格
     */
    public final  String pa;

    /**
     * 用户id
     */
    public final  String ui;

    /**
     *	爆仓价格
     */
    public final  String pf;

    /**
     *	仓位模式: 1全仓, 2逐仓
     */
    public final  Integer md;

    /**
     *	可平仓位价值
     */
    public final  BigDecimal lc;

    /**
     *	交易对
     */
    public final  String pi;

    /**
     *	保证金
     */
    public final  String mg;

    /**
     *	仓位价值 = 合约张数X合约面值
     */
    public final  BigDecimal hc;

    /**
     *  开仓价格
     */
    public final  BigDecimal po;

    /**
     * {
     *             "pt":"-0.000000003",
     *             "f":"0.000000057",
     *             "l":"10",
     *             "sd":1,
     *             "pa":"19311700",
     *             "ui":11964919,
     *             "fb0":"0",
     *             "pf":"19311700",
     *             "md":1,
     *             "lc":"0",
     *             "pi":"5BTC_USD",
     *             "mg":"0",
     *             "hc":"0",
     *             "fb":"0",
     *             "po":"19278"
     *         }
     * */
    public BiboxCoinSwapPostionResp(
            @JsonProperty("l") Integer l,
            @JsonProperty("sd")  Integer sd,
            @JsonProperty("pa")  String pa,
            @JsonProperty("ui")  String ui,
            @JsonProperty("pf")  String pf,
            @JsonProperty("md")  Integer md,
            @JsonProperty("lc")  BigDecimal lc,
            @JsonProperty("pi")  String pi,
            @JsonProperty("mg")  String mg,
            @JsonProperty("hc")  BigDecimal hc,
            @JsonProperty("po")  BigDecimal po) {
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

    public Integer getL() {
        return l;
    }

    public Integer getSd() {
        return sd;
    }

    public String getPa() {
        return pa;
    }

    public String getUi() {
        return ui;
    }

    public String getPf() {
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

    public String getMg() {
        return mg;
    }

    public BigDecimal getHc() {
        return hc;
    }

    public BigDecimal getPo() {
        return po;
    }
}
