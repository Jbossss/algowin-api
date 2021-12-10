package org.market.hedge.deribit.service;

import org.market.hedge.deribit.DeribitExchange;
import org.market.hedge.deribit.dto.Kind;
import org.market.hedge.deribit.dto.account.AccountSummary;
import org.market.hedge.deribit.dto.account.Position;

import java.io.IOException;
import java.util.List;

public class DeribitAccountServiceRaw extends DeribitBaseService {

  public DeribitAccountServiceRaw(DeribitExchange exchange) {
    super(exchange);
  }

  public AccountSummary getAccountSummary(String currency, Boolean extended) throws IOException {
    return deribitAuthenticated.getAccountSummary(currency, extended, deribitAuth).getResult();
  }

  public List<Position> getPositions(String currency, Kind kind) throws IOException {
    return deribitAuthenticated.getPositions(currency, kind, deribitAuth).getResult();
  }
}
