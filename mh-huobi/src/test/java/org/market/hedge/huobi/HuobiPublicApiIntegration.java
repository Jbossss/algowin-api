package org.market.hedge.huobi;

import org.junit.Before;
import org.junit.Test;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.service.marketdata.MarketDataService;
import org.market.hedge.MHExchangeFactory;
import org.market.hedge.core.TradingArea;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.Ticker;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class HuobiPublicApiIntegration {

  private Exchange exchange;

  @Before
  public void setup() {
    exchange = MHExchangeFactory.INSTANCE.createExchange(HuobiExchange.class.getName(), TradingArea.Spot);
  }

  @Test
  public void getTickerTest() throws Exception {
    MarketDataService marketDataService = exchange.getMarketDataService();
    Ticker ticker = marketDataService.getTicker(CurrencyPair.BTC_USDT);

    assertThat(ticker).isNotNull();
    assertThat(ticker.getBid()).isGreaterThan(BigDecimal.ZERO);
    assertThat(ticker.getAsk()).isGreaterThan(BigDecimal.ZERO);
  }

  @Test
  public void getAllTickerTest() throws Exception {
    MarketDataService marketDataService = exchange.getMarketDataService();
    List<Ticker> tickers = marketDataService.getTickers(null);

    assertThat(tickers).isNotNull();

    assertThat(tickers.get(0).getBid()).isGreaterThan(BigDecimal.ZERO);
    assertThat(tickers.get(0).getAsk()).isGreaterThan(BigDecimal.ZERO);
    assertThat(tickers.get(0).getBidSize()).isGreaterThan(BigDecimal.ZERO);
    assertThat(tickers.get(0).getAskSize()).isGreaterThan(BigDecimal.ZERO);
  }

  @Test
  public void getExchangeSymbolsTest() {
    List<CurrencyPair> exchangeSymbols = exchange.getExchangeSymbols();

    assertThat(exchangeSymbols).isNotNull();
    assertThat(exchangeSymbols).size().isGreaterThan(0);
  }
}
