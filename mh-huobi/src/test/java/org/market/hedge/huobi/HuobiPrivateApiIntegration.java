package org.market.hedge.huobi;

import org.junit.*;
import org.knowm.xchange.service.trade.TradeService;
import org.market.hedge.MHExchange;
import org.market.hedge.MHExchangeFactory;
import org.market.hedge.core.TradingArea;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.Order.OrderType;
import org.knowm.xchange.dto.account.Balance;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.dto.trade.MarketOrder;
import org.knowm.xchange.dto.trade.OpenOrders;
import org.market.hedge.huobi.dto.account.HuobiAccount;
import org.market.hedge.huobi.service.HuobiAccountService;
import org.knowm.xchange.service.account.AccountService;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

public class HuobiPrivateApiIntegration {

  private HuobiProperties properties;
  private MHExchange exchange;

  @Before
  public void setup() throws IOException {
    properties = new HuobiProperties();
    Assume.assumeTrue("Ignore tests because credentials are missing", properties.isValid());

    exchange =
        MHExchangeFactory.INSTANCE.createExchange(
            HuobiExchange.class.getName(), properties.getApiKey(), properties.getSecretKey(), TradingArea.Spot);
  }

  @After
  public void teardown() throws IOException {
    if (exchange != null) {
      for (LimitOrder order : exchange.getTradeService().getOpenOrders().getOpenOrders()) {
        exchange.getTradeService().cancelOrder(order.getId());
      }
    }
  }

  @Test
  public void getAccountTest() throws IOException {
    HuobiAccountService accountService = (HuobiAccountService) exchange.getAccountService();
    HuobiAccount[] accounts = accountService.getAccounts();
    System.out.println(Arrays.toString(accounts));
  }

  @Test
  public void getBalanceTest() throws IOException {
    AccountService accountService = exchange.getAccountService();
    Balance balance = accountService.getAccountInfo().getWallet().getBalance(Currency.USDT);
    System.out.println(balance.toString());
    assertThat(balance).isNotNull();
  }

  @Test
  public void getOpenOrdersTest() throws IOException {
    TradeService tradeService = exchange.getTradeService();
    OpenOrders openOrders = tradeService.getOpenOrders();
    System.out.println(openOrders.toString());
    assertThat(openOrders).isNotNull();
  }

  @Test
  public void getOrderTest() throws IOException {
    TradeService tradeService = exchange.getTradeService();
    Collection<Order> orders = tradeService.getOrder("2132866355");
    System.out.println(orders.toString());
    assertThat(orders).isNotNull();
  }

  @Test
  public void placeLimitOrderTest() throws IOException {
    String orderId = placePendingOrder();
    System.out.println(orderId);
  }

  private String placePendingOrder() throws IOException {
    TradeService tradeService = exchange.getTradeService();
    HuobiAccountService accountService = (HuobiAccountService) exchange.getAccountService();
    HuobiAccount[] accounts = accountService.getAccounts();
    LimitOrder LimitOrder =
        new LimitOrder(
            OrderType.BID,
            new BigDecimal("0.001"),
            new CurrencyPair("BTC", "USDT"),
            String.valueOf(accounts[0].getId()),
            null,
            new BigDecimal("10000"));
    return tradeService.placeLimitOrder(LimitOrder);
  }

  @Test
  public void placeMarketOrderTest() throws IOException {
    TradeService tradeService = exchange.getTradeService();
    HuobiAccountService accountService = (HuobiAccountService) exchange.getAccountService();
    HuobiAccount[] accounts = accountService.getAccounts();
    MarketOrder marketOrder =
        new MarketOrder(
            OrderType.ASK,
            new BigDecimal("0.0002"),
            new CurrencyPair("BTC", "USDT"),
            String.valueOf(accounts[0].getId()),
            null);
    String orderId = tradeService.placeMarketOrder(marketOrder);
    System.out.println(orderId);
  }

  @Test
  @Ignore("Use it for manual")
  public void cancelOrderTest() throws IOException {
    TradeService tradeService = exchange.getTradeService();
    boolean result = tradeService.cancelOrder("2134551697");
    System.out.println(result);
  }
}
