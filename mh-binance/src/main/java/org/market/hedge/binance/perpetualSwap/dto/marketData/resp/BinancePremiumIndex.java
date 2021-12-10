package org.market.hedge.binance.perpetualSwap.dto.marketData.resp;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.market.hedge.core.PremiumIndex;

import java.math.BigDecimal;

public class BinancePremiumIndex extends PremiumIndex {

    public BinancePremiumIndex(
            @JsonProperty("symbol")  String symbol,
            @JsonProperty("markPrice") BigDecimal markPrice,
            @JsonProperty("indexPrice")  BigDecimal indexPrice,
            @JsonProperty("lastFundingRate")   BigDecimal lastFundingRate,
            @JsonProperty("nextFundingTime")  long nextFundingTime,
            @JsonProperty("interestRate")  BigDecimal interestRate,
            @JsonProperty("time")  long time) {
        super(symbol,markPrice,indexPrice,lastFundingRate,nextFundingTime,interestRate,time);
    }

}
