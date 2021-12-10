package org.market.hedge.exception;

import org.market.hedge.core.TradingArea;

public class NullTradingAreaException extends RuntimeException{

    /**
     * Constructs an <code>ExchangeException</code> with the specified detail message.
     *
     * @param message the detail message.
     */
    public NullTradingAreaException(String message) {
        super(message);
    }


    /**
     * Constructs an <code>ExchangeException</code> with the specified detail message.
     *
     * @param tradingArea the detail message.
     */
    public NullTradingAreaException(TradingArea tradingArea) {
        super("The instance does not implement the "+tradingArea.name()+" transaction module");
    }



}
