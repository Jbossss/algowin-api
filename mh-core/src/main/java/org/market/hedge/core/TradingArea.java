package org.market.hedge.core;

public enum TradingArea {

    /**
     * 现货，币币
     * */
    Spot,

    /**
     * 杠杆交易
     * */
    Margin,

    /**
     * usdt永续和约
     * */
    PerpetualSwap,

    /**
     * 期货交易
     * */
    Futures,

    /**
     * 期权
     * */
    Option,

    /**
    * 币本位永续
    * */
    CoinSwap;

}
