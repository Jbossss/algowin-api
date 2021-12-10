package org.market.hedge.deribit.service;

import org.knowm.xchange.dto.account.AccountInfo;
import org.knowm.xchange.dto.account.Balance;
import org.knowm.xchange.dto.account.Wallet;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.service.account.AccountService;
import org.market.hedge.deribit.DeribitAdapters;
import org.market.hedge.deribit.DeribitExchange;
import org.market.hedge.deribit.dto.account.AccountSummary;

import java.io.IOException;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DeribitAccountService extends DeribitAccountServiceRaw implements AccountService {

  /** currently there are only two (base) currencies supported by deribit */
  private final Supplier<Stream<String>> currencies = () -> Stream.of("BTC", "ETH");

  public DeribitAccountService(DeribitExchange exchange) {
    super(exchange);
  }

  @Override
  public AccountInfo getAccountInfo() throws IOException {
    List<Balance> balances =
        currencies
            .get()
            .parallel()
            .map(
                c -> {
                  try {
                    AccountSummary accountSummary = super.getAccountSummary(c, false);
                    return DeribitAdapters.adapt(accountSummary);
                  } catch (IOException e) {
                    throw new ExchangeException(e);
                  }
                })
            .collect(Collectors.toList());
    Wallet w = Wallet.Builder.from(balances).build();
    return new AccountInfo(w);
  }
}
