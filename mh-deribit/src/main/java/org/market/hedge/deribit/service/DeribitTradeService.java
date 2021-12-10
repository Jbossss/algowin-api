package org.market.hedge.deribit.service;

import org.market.hedge.deribit.DeribitExchange;
import org.market.hedge.deribit.dto.trade.Order;
import org.market.hedge.deribit.dto.trade.OrderState;
import org.knowm.xchange.service.trade.TradeService;

import java.io.IOException;

public class DeribitTradeService extends DeribitTradeServiceRaw implements TradeService {

  public DeribitTradeService(DeribitExchange exchange) {
    super(exchange);
  }

  @Override
  public boolean cancelOrder(String orderId) throws IOException {
    Order cancel = super.cancel(orderId);
    return cancel.getOrderState() == OrderState.cancelled;
  }


}
