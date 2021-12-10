package org.market.hedge.bitget.usdtswap.dto.marketdata.resp;

import java.math.BigDecimal;
import java.util.Date;

public class BitgeUSwapTicker {

    private String symbol;
    private BigDecimal best_ask;
    private BigDecimal best_bid;
    private BigDecimal high_24h;
    private BigDecimal last;
    private BigDecimal low_24h;
    private Date timeStamp;
    private int volume_24h;
    private BigDecimal priceChangePercent;
    private BigDecimal base_volume;

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setBest_ask(BigDecimal best_ask) {
        this.best_ask = best_ask;
    }

    public void setBest_bid(BigDecimal best_bid) {
        this.best_bid = best_bid;
    }

    public void setHigh_24h(BigDecimal high_24h) {
        this.high_24h = high_24h;
    }

    public void setLast(BigDecimal last) {
        this.last = last;
    }

    public void setLow_24h(BigDecimal low_24h) {
        this.low_24h = low_24h;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setVolume_24h(int volume_24h) {
        this.volume_24h = volume_24h;
    }

    public void setPriceChangePercent(BigDecimal priceChangePercent) {
        this.priceChangePercent = priceChangePercent;
    }

    public void setBase_volume(BigDecimal base_volume) {
        this.base_volume = base_volume;
    }

    public String getSymbol() {
        return symbol;
    }

    public BigDecimal getBest_ask() {
        return best_ask;
    }

    public BigDecimal getBest_bid() {
        return best_bid;
    }

    public BigDecimal getHigh_24h() {
        return high_24h;
    }

    public BigDecimal getLast() {
        return last;
    }

    public BigDecimal getLow_24h() {
        return low_24h;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public int getVolume_24h() {
        return volume_24h;
    }

    public BigDecimal getPriceChangePercent() {
        return priceChangePercent;
    }

    public BigDecimal getBase_volume() {
        return base_volume;
    }

    @Override
    public String toString() {
        return "BitgeUSwapTicker{" +
                "symbol='" + symbol + '\'' +
                ", best_ask=" + best_ask +
                ", best_bid=" + best_bid +
                ", high_24h=" + high_24h +
                ", last=" + last +
                ", low_24h=" + low_24h +
                ", timeStamp=" + timeStamp +
                ", volume_24h=" + volume_24h +
                ", priceChangePercent=" + priceChangePercent +
                ", base_volume=" + base_volume +
                '}';
    }
}
