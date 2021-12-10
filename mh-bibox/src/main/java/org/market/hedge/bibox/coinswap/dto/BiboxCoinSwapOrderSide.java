package org.market.hedge.bibox.coinswap.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.knowm.xchange.dto.Order.OrderType;
import org.knowm.xchange.exceptions.ExchangeException;

/** @author odrotleff */
public enum BiboxCoinSwapOrderSide {
  BID(1, OrderType.BID),
  ASK(2, OrderType.ASK),
  EXIT_BID(3, OrderType.EXIT_BID),
  EXIT_ASK(4, OrderType.EXIT_ASK);

  private int orderSide;
  private OrderType orderType;

  private BiboxCoinSwapOrderSide(int orderSide, OrderType orderType) {
    this.orderSide = orderSide;
    this.orderType = orderType;
  }

  public static BiboxCoinSwapOrderSide fromOrderType(OrderType orderType) {
    switch (orderType) {
      case BID:
        return BiboxCoinSwapOrderSide.BID;
      case ASK:
        return BiboxCoinSwapOrderSide.ASK;
      case EXIT_BID:
        return BiboxCoinSwapOrderSide.EXIT_BID;
      case EXIT_ASK:
        return BiboxCoinSwapOrderSide.EXIT_ASK;
      default:
        throw new ExchangeException("Order type " + orderType + " unsupported.");
    }
  }

  @JsonCreator
  public static BiboxCoinSwapOrderSide fromInt(int orderSide) {
    switch (orderSide) {
      case 1:
        return BID;
      case 2:
        return ASK;
      case 3:
        return EXIT_BID;
      case 4:
        return EXIT_ASK;
      default:
        throw new ExchangeException("Unexpected Bibox order side.");
    }
  }

  public int asInt() {
    return orderSide;
  }

  public OrderType getOrderType() {
    return orderType;
  }
}
