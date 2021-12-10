package org.market.hedge.core;

public enum  Direction {

    Call(1),

    Put(-1);

    private final Integer direction;
    private Direction(Integer direction) {
        this.direction = direction;
    }

}
