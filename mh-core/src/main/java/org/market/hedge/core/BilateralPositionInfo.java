package org.market.hedge.core;

public class BilateralPositionInfo {

    PositionInfo buyPosition;

    PositionInfo sellPosition;

    public BilateralPositionInfo(PositionInfo buyPosition, PositionInfo sellPosition) {
        this.buyPosition = buyPosition;
        this.sellPosition = sellPosition;
    }

    public PositionInfo getBuyPosition() {
        return buyPosition;
    }

    public PositionInfo getSellPosition() {
        return sellPosition;
    }

    public void setBuyPosition(PositionInfo buyPosition) {
        this.buyPosition = buyPosition;
    }

    public void setSellPosition(PositionInfo sellPosition) {
        this.sellPosition = sellPosition;
    }
}
