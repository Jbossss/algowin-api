package org.market.hedge.binance.option.dto.marketdat.resq;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OptionInfo {

    /**
     *"id": 1,
     */
    @JsonProperty("id")
    private Long id;

    /**
     *"contractId": 1,
     */
    @JsonProperty("contractId")
    private Long contractId;

    /**
     * "underlying": "BTCUSDT",          // 合约标的
     */
    @JsonProperty("underlying")
    private String underlying;

    /**
     * "quoteAsset": "USDT",             // 计价资产
     */
    @JsonProperty("quoteAsset")
    private String quoteAsset;

    /**
     * "symbol": "BTC-200730-9000-C",    // 交易对名称
     */
    @JsonProperty("symbol")
    private String symbol;

    /**
     * "unit": 1,                        // 兑换比例，一张合约代表的标的数量
     */
    @JsonProperty("unit")
    private BigDecimal unit;

    /**
     * "minQty": 1,                      // 合约标的最少交易量
     */
    @JsonProperty("minQty")
    private BigDecimal minQty;

    /**
     * "side": "CALL",                   // 方向: CALL 多, PUT 空
     */
    @JsonProperty("side")
    private String side;

    /**
     * "leverage": 0,                    // 杠杆
     */
    @JsonProperty("leverage")
    private Integer leverage;

    /**
     * "strikePrice": 9000,              // 行权价格
     */
    @JsonProperty("strikePrice")
    private BigDecimal strikePrice;

    /**
     * "expiryDate": 1593518400000       // 行权时间
     */
    @JsonProperty("expiryDate")
    private BigDecimal expiryDate;

}
