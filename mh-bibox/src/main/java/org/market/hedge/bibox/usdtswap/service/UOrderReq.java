package org.market.hedge.bibox.usdtswap.service;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UOrderReq {
    private String symbol;
    private int leverage;
    private int crossLeverage;
    private int side;
    private BigDecimal price;
    private int amount;
    private long clientOID;
}
