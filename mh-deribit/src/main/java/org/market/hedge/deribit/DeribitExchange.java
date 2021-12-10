package org.market.hedge.deribit;

import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.currency.CurrencyPair;
import org.market.hedge.BaseMHExchange;
import org.market.hedge.MHExchange;
import org.market.hedge.MHExchangeSpecification;
import org.market.hedge.core.TradingArea;
import org.market.hedge.deribit.dto.marketdata.DeribitCurrency;
import org.market.hedge.deribit.dto.marketdata.DeribitInstrument;
import org.market.hedge.deribit.option.service.DeribitOptionAccountService;
import org.market.hedge.deribit.option.service.DeribitOptionMarketDataService;
import org.market.hedge.deribit.option.service.DeribitOptionTradeService;
import org.market.hedge.deribit.perpetualSwap.service.DeribitPerpetualSwapMarketDataService;
import org.market.hedge.deribit.perpetualSwap.service.DeribitPerpetualSwapTradeDataService;
import org.market.hedge.deribit.service.DeribitAccountService;
import org.market.hedge.deribit.service.DeribitMarketDataServiceRaw;
import org.knowm.xchange.dto.meta.CurrencyMetaData;
import org.knowm.xchange.dto.meta.CurrencyPairMetaData;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.utils.nonce.AtomicLongCurrentTimeIncrementalNonceFactory;
import org.market.hedge.exception.NullTradingAreaException;
import si.mazi.rescu.SynchronizedValueFactory;

import java.io.IOException;
import java.util.*;

public class DeribitExchange extends BaseMHExchange implements MHExchange {

  private SynchronizedValueFactory<Long> nonceFactory =
      new AtomicLongCurrentTimeIncrementalNonceFactory();

  @Override
  public void applySpecification(ExchangeSpecification exchangeSpecification) {

    super.applySpecification(exchangeSpecification);
  }

  @Override
  protected void initServices() {
    switch (mHexchangeSpecification.getTradingArea()) {
      case Option:
        this.mHmarketDataService = new DeribitOptionMarketDataService(this);
        this.mHaccountService = new DeribitOptionAccountService(this);
        this.mHtradeService = new DeribitOptionTradeService(this);
        break;
      case PerpetualSwap:
        this.mHmarketDataService = new DeribitPerpetualSwapMarketDataService(this);
        this.accountService = new DeribitAccountService(this);
        this.mHtradeService = new DeribitPerpetualSwapTradeDataService(this);
        break;
      default:
        throw new NullTradingAreaException(mHexchangeSpecification.getTradingArea());

    }
  }

  @Override
  public MHExchangeSpecification getDefaultExchangeSpecification(TradingArea tradingArea) {
    this.streamingParsing=new DeribitStreamingParsing(tradingArea);
    MHExchangeSpecification exchangeSpecification =
            new MHExchangeSpecification(this.getClass().getCanonicalName());
    exchangeSpecification.setTradingArea(tradingArea);
    exchangeSpecification.setSslUri("https://www.deribit.com");
    exchangeSpecification.setHost("deribit.com");
    //    exchangeSpecification.setPort(80);
    exchangeSpecification.setExchangeName("Deribit");
    exchangeSpecification.setExchangeDescription("Deribit is a Bitcoin futures exchange");
    return exchangeSpecification;
  }

  @Override
  public ExchangeSpecification getDefaultExchangeSpecification() {

    ExchangeSpecification exchangeSpecification =
        new ExchangeSpecification(this.getClass().getCanonicalName());
    exchangeSpecification.setSslUri("https://www.deribit.com");
    exchangeSpecification.setHost("deribit.com");
    //    exchangeSpecification.setPort(80);
    exchangeSpecification.setExchangeName("Deribit");
    exchangeSpecification.setExchangeDescription("Deribit is a Bitcoin futures exchange");
    return exchangeSpecification;
  }

  public ExchangeSpecification getSandboxExchangeSpecification() {

    ExchangeSpecification exchangeSpecification =
        new ExchangeSpecification(this.getClass().getCanonicalName());
    exchangeSpecification.setSslUri("https://www.deribit.com/");
    exchangeSpecification.setHost("www.deribit.com");
    //    exchangeSpecification.setPort(80);
    return exchangeSpecification;
  }

  @Override
  public SynchronizedValueFactory<Long> getNonceFactory() {
    return nonceFactory;
  }

  @Override
  public void remoteInit() throws IOException {
    //updateExchangeMetaData();
  }

  public void updateExchangeMetaData() throws IOException {

    List<CurrencyPair> activeCurrencyPairs = new ArrayList<>();
    Set<Currency> activeCurrencies = new HashSet<>();

    List<DeribitCurrency> activeDeribitCurrencies =
        ((DeribitMarketDataServiceRaw) marketDataService).getDeribitCurrencies();

    List<DeribitInstrument> activeDeribitInstruments = new ArrayList<>();
    for (DeribitCurrency deribitCurrency : activeDeribitCurrencies) {
      activeDeribitInstruments.addAll(
          ((DeribitMarketDataServiceRaw) marketDataService)
              .getDeribitInstruments(deribitCurrency.getCurrency(), null, null));
    }

    activeDeribitInstruments.forEach(
        activeDeribitInstrument ->
            collectMetaData(activeDeribitInstrument, activeCurrencyPairs, activeCurrencies));

    Map<CurrencyPair, CurrencyPairMetaData> pairsMap = exchangeMetaData.getCurrencyPairs();
    Map<Currency, CurrencyMetaData> currenciesMap = exchangeMetaData.getCurrencies();

    // Remove pairs that are no-longer in use
    pairsMap.keySet().retainAll(activeCurrencyPairs);

    // Remove currencies that are no-longer in use
    currenciesMap.keySet().retainAll(activeCurrencies);

    // Add missing pairs and currencies
    activeCurrencyPairs.forEach(
        cp -> {
          if (!pairsMap.containsKey(cp)) {
            pairsMap.put(cp, null);
          }
          if (!currenciesMap.containsKey(cp.base)) {
            currenciesMap.put(cp.base, null);
          }
          if (!currenciesMap.containsKey(cp.counter)) {
            currenciesMap.put(cp.counter, null);
          }
        });
  }

  private String extractContractName(DeribitInstrument instrument) {
    String[] temp = instrument.getInstrumentName().split("-", 2);
    if (temp.length > 0) {
      return temp[1];
    }
    throw new ExchangeException("Extracting contract name failed");
  }

  private void collectMetaData(
      DeribitInstrument instrument,
      List<CurrencyPair> activeCurrencyPairs,
      Set<Currency> activeCurrencies) {

    instrument.getBaseCurrency();
    String baseSymbol = instrument.getBaseCurrency();
    String counterSymbol = extractContractName(instrument);

    activeCurrencies.add(new Currency(baseSymbol));
    activeCurrencies.add(new Currency(counterSymbol));

    activeCurrencyPairs.add(new CurrencyPair(baseSymbol, counterSymbol));
  }


}
