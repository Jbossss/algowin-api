package org.market.hedge.binance;

import org.knowm.xchange.ExchangeSpecification;
import org.market.hedge.BaseMHExchange;
import org.market.hedge.MHExchange;
import org.market.hedge.MHExchangeSpecification;
import org.market.hedge.binance.dto.meta.exchangeinfo.BinanceExchangeInfo;
import org.market.hedge.binance.option.BinanceOptionAuthenticated;
import org.market.hedge.binance.option.service.BinanceOptionAccountService;
import org.market.hedge.binance.option.service.BinanceOptionMarketDataService;
import org.market.hedge.binance.option.service.BinanceOptionTradeService;
import org.market.hedge.binance.perpetualSwap.BinancePerpetualAuthenticated;
import org.market.hedge.binance.perpetualSwap.service.BinancePerpetualAccountService;
import org.market.hedge.binance.perpetualSwap.service.BinancePerpetualMarketDataService;
import org.market.hedge.binance.perpetualSwap.service.BinancePerpetualTradeService;
import org.market.hedge.binance.service.BinanceAccountService;
import org.market.hedge.binance.service.BinanceMarketDataService;
import org.market.hedge.binance.service.BinanceTradeService;
import org.knowm.xchange.client.ExchangeRestProxyBuilder;
import org.knowm.xchange.client.ResilienceRegistries;
import org.knowm.xchange.utils.AuthUtils;
import org.market.hedge.core.TradingArea;
import org.market.hedge.exception.NullTradingAreaException;
import si.mazi.rescu.SynchronizedValueFactory;

import java.math.BigDecimal;

public class BinanceExchange extends BaseMHExchange implements MHExchange {

  private static ResilienceRegistries RESILIENCE_REGISTRIES;

  private BinanceExchangeInfo exchangeInfo;
  private BinanceAuthenticated binance;
  private BinanceOptionAuthenticated binanceOption;
  private BinancePerpetualAuthenticated binancePerpetual;
  private SynchronizedValueFactory<Long> timestampFactory;

  @Override
  protected void initServices() {

    this.timestampFactory =
            new BinanceTimestampFactory(
                    binance, getExchangeSpecification().getResilience(), getResilienceRegistries());
    switch (mHexchangeSpecification.getTradingArea()){
      case Spot:
        this.binance =
                ExchangeRestProxyBuilder.forInterface(
                        BinanceAuthenticated.class, getExchangeSpecification())
                        .build();
        this.marketDataService = new BinanceMarketDataService(this, binance, getResilienceRegistries());
        this.tradeService = new BinanceTradeService(this, binance, getResilienceRegistries());
        this.accountService = new BinanceAccountService(this, binance, getResilienceRegistries());
        break;
      case PerpetualSwap:
      case Futures:
        this.binancePerpetual =
                ExchangeRestProxyBuilder.forInterface(
                        BinancePerpetualAuthenticated.class, getExchangeSpecification())
                        .build();
        this.mHmarketDataService= new BinancePerpetualMarketDataService(this, binancePerpetual, getResilienceRegistries());
        this.mHtradeService = new BinancePerpetualTradeService(this, binancePerpetual, getResilienceRegistries());
        this.mHaccountService = new BinancePerpetualAccountService(this, binancePerpetual, getResilienceRegistries());
        break;
      case Option:
        this.binanceOption =
                ExchangeRestProxyBuilder.forInterface(
                        BinanceOptionAuthenticated.class, getExchangeSpecification())
                        .build();
        this.mHmarketDataService= new BinanceOptionMarketDataService(this, binanceOption, getResilienceRegistries());
        this.mHtradeService = new BinanceOptionTradeService(this, binanceOption, getResilienceRegistries());
        this.mHaccountService = new BinanceOptionAccountService(this, binanceOption, getResilienceRegistries());
        break;
      default:
        break;
    }

  }

  public SynchronizedValueFactory<Long> getTimestampFactory() {
    return timestampFactory;
  }

  @Override
  public SynchronizedValueFactory<Long> getNonceFactory() {
    throw new UnsupportedOperationException(
        "Binance uses timestamp/recvwindow rather than a nonce");
  }

  public static void resetResilienceRegistries() {
    RESILIENCE_REGISTRIES = null;
  }

  @Override
  public ResilienceRegistries getResilienceRegistries() {
    if (RESILIENCE_REGISTRIES == null) {
      RESILIENCE_REGISTRIES = BinanceResilience.createRegistries();
    }
    return RESILIENCE_REGISTRIES;
  }

  @Override
  public ExchangeSpecification getDefaultExchangeSpecification() {

    ExchangeSpecification spec = new ExchangeSpecification(this.getClass().getCanonicalName());
    spec.setSslUri("https://api.binance.com");
    spec.setHost("www.binance.com");
    spec.setPort(80);
    spec.setExchangeName("Binance");
    spec.setExchangeDescription("Binance Exchange.");
    AuthUtils.setApiAndSecretKey(spec, "binance");
    return spec;
  }

  public BinanceExchangeInfo getExchangeInfo() {

    return exchangeInfo;
  }

  @Override
  public void remoteInit() {
   /* try {
      // populate currency pair keys only, exchange does not provide any other metadata for download
      Map<CurrencyPair, CurrencyPairMetaData> currencyPairs = exchangeMetaData.getCurrencyPairs();
      Map<Currency, CurrencyMetaData> currencies = exchangeMetaData.getCurrencies();

      BinanceMarketDataService marketDataService =
          (BinanceMarketDataService) this.marketDataService;
      exchangeInfo = marketDataService.getExchangeInfo();
      Symbol[] symbols = exchangeInfo.getSymbols();

      BinanceAccountService accountService = (BinanceAccountService) getAccountService();
      Map<String, AssetDetail> assetDetailMap = accountService.getAssetDetails();
      // Clear all hardcoded currencies when loading dynamically from exchange.
      if (assetDetailMap != null) {
        currencies.clear();
      }
      for (Symbol symbol : symbols) {
        if (symbol.getStatus().equals("TRADING")) { // Symbols which are trading
          int basePrecision = Integer.parseInt(symbol.getBaseAssetPrecision());
          int counterPrecision = Integer.parseInt(symbol.getQuotePrecision());
          int pairPrecision = 8;
          int amountPrecision = 8;

          BigDecimal minQty = null;
          BigDecimal maxQty = null;
          BigDecimal stepSize = null;

          BigDecimal counterMinQty = null;
          BigDecimal counterMaxQty = null;

          Filter[] filters = symbol.getFilters();

          CurrencyPair currentCurrencyPair =
              new CurrencyPair(symbol.getBaseAsset(), symbol.getQuoteAsset());

          for (Filter filter : filters) {
            if (filter.getFilterType().equals("PRICE_FILTER")) {
              pairPrecision = Math.min(pairPrecision, numberOfDecimals(filter.getTickSize()));
              counterMaxQty = new BigDecimal(filter.getMaxPrice()).stripTrailingZeros();
            } else if (filter.getFilterType().equals("LOT_SIZE")) {
              amountPrecision = Math.min(amountPrecision, numberOfDecimals(filter.getStepSize()));
              minQty = new BigDecimal(filter.getMinQty()).stripTrailingZeros();
              maxQty = new BigDecimal(filter.getMaxQty()).stripTrailingZeros();
              stepSize = new BigDecimal(filter.getStepSize()).stripTrailingZeros();
            } else if (filter.getFilterType().equals("MIN_NOTIONAL")) {
              counterMinQty = new BigDecimal(filter.getMinNotional()).stripTrailingZeros();
            }
          }

          boolean marketOrderAllowed = Arrays.asList(symbol.getOrderTypes()).contains("MARKET");
          currencyPairs.put(
              currentCurrencyPair,
              new CurrencyPairMetaData(
                  new BigDecimal("0.1"), // Trading fee at Binance is 0.1 %
                  minQty, // Min amount
                  maxQty, // Max amount
                  counterMinQty,
                  counterMaxQty,
                  amountPrecision, // base precision
                  pairPrecision, // counter precision
                  null, *//* TODO get fee tiers, although this is not necessary now
                        because their API returns current fee directly *//*
                  stepSize,
                  null,
                  marketOrderAllowed));

          Currency baseCurrency = currentCurrencyPair.base;
          CurrencyMetaData baseCurrencyMetaData =
              BinanceAdapters.adaptCurrencyMetaData(
                  currencies, baseCurrency, assetDetailMap, basePrecision);
          currencies.put(baseCurrency, baseCurrencyMetaData);

          Currency counterCurrency = currentCurrencyPair.counter;
          CurrencyMetaData counterCurrencyMetaData =
              BinanceAdapters.adaptCurrencyMetaData(
                  currencies, counterCurrency, assetDetailMap, counterPrecision);
          currencies.put(counterCurrency, counterCurrencyMetaData);
        }
      }
    } catch (Exception e) {
      throw new ExchangeException("Failed to initialize: " + e.getMessage(), e);
    }*/
  }

  private int numberOfDecimals(String value) {

    return new BigDecimal(value).stripTrailingZeros().scale();
  }

  @Override
  public MHExchangeSpecification getDefaultExchangeSpecification(TradingArea tradingArea) {
    MHExchangeSpecification spec = new MHExchangeSpecification(this.getClass().getCanonicalName());
    spec.setHost("www.binance.com");
    spec.setPort(80);
    spec.setExchangeName("Binance");
    spec.setExchangeDescription("Binance Exchange.");
    spec.setTradingArea(tradingArea);
    AuthUtils.setApiAndSecretKey(spec, "binance");
    this.streamingParsing=new BinanceStreamingParsing(tradingArea);

    switch (tradingArea) {
      case Spot:
        spec.setSslUri("https://api.binance.com");
        return spec;
      case PerpetualSwap:
      case Futures:
        spec.setSslUri("https://fapi.binance.com");
        return spec;
      case Option:
        spec.setSslUri("https://vapi.binance.com");
        return spec;
      default:
        break;
    }
    throw new NullTradingAreaException(tradingArea);
  }

}
