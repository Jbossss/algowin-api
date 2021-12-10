package org.market.hedge.huobi;

import org.market.hedge.BaseMHExchange;
import org.market.hedge.MHExchange;
import org.market.hedge.MHExchangeSpecification;
import org.market.hedge.core.TradingArea;
import org.knowm.xchange.exceptions.ExchangeException;
import org.market.hedge.exception.NullTradingAreaException;
import org.market.hedge.huobi.dto.marketdata.HuobiAsset;
import org.market.hedge.huobi.dto.marketdata.HuobiAssetPair;
import org.market.hedge.huobi.futures.service.HuobiFuturesAccountService;
import org.market.hedge.huobi.futures.service.HuobiFuturesMarketDataService;
import org.market.hedge.huobi.futures.service.HuobiFuturesTradeService;
import org.market.hedge.huobi.service.HuobiAccountService;
import org.market.hedge.huobi.service.HuobiMarketDataService;
import org.market.hedge.huobi.service.HuobiMarketDataServiceRaw;
import org.market.hedge.huobi.service.HuobiTradeService;
import org.market.hedge.huobi.option.service.HuobiOptionAccountService;
import org.market.hedge.huobi.option.service.HuobiOptionMarketDataService;
import org.market.hedge.huobi.option.service.HuobiOptionTradeService;
import org.knowm.xchange.utils.nonce.CurrentTimeNonceFactory;
import org.market.hedge.huobi.coinSwap.service.HuobiSwapMarketDataService;
import org.market.hedge.huobi.coinSwap.service.HuobiSwapTradeService;
import org.market.hedge.huobi.usdtSwap.service.HuobiUsdtSwapMarketDataService;
import org.market.hedge.huobi.usdtSwap.service.HuobiUsdtSwapTradeService;
import si.mazi.rescu.SynchronizedValueFactory;

import java.io.IOException;

public class HuobiExchange extends BaseMHExchange implements MHExchange {

  private final SynchronizedValueFactory<Long> nonceFactory = new CurrentTimeNonceFactory();

  @Override
  protected void initServices() {
    switch (mHexchangeSpecification.getTradingArea()){
      case Spot:
        this.mHmarketDataService = new HuobiMarketDataService(this);
        this.mHtradeService = new HuobiTradeService(this);
        this.mHaccountService = new HuobiAccountService(this);
        break;
      case Option:
        this.mHmarketDataService = new HuobiOptionMarketDataService(this);
        this.mHtradeService = new HuobiOptionTradeService(this);
        this.mHaccountService = new HuobiOptionAccountService(this);
        break;
      case CoinSwap:
        this.mHmarketDataService=new HuobiSwapMarketDataService(this);
        this.mHtradeService=new HuobiSwapTradeService(this);
        break;
      case Futures:
        this.mHmarketDataService=new HuobiFuturesMarketDataService(this);
        this.mHtradeService=new HuobiFuturesTradeService(this);
        this.mHaccountService=new HuobiFuturesAccountService(this);
        break;
      case PerpetualSwap:
        this.mHmarketDataService=new HuobiUsdtSwapMarketDataService(this);
        this.mHtradeService=new HuobiUsdtSwapTradeService(this);
        break;
      default:
        throw new NullTradingAreaException(mHexchangeSpecification.getTradingArea());
    }

  }

  @Override
  public MHExchangeSpecification getDefaultExchangeSpecification(TradingArea tradingArea) {
    this.streamingParsing=new HuobiStreamingParsing(tradingArea);
    MHExchangeSpecification exchangeSpecification =
        new MHExchangeSpecification(this.getClass().getCanonicalName());
    exchangeSpecification.setTradingArea(tradingArea);
    switch (tradingArea){
      case Spot:
        exchangeSpecification.setSslUri("https://api.huobi.pro");
        exchangeSpecification.setHost("api.huobi.pro");
        exchangeSpecification.setPort(80);
        exchangeSpecification.setExchangeName("Huobi");
        exchangeSpecification.setExchangeDescription(
                "Huobi is a Chinese digital currency trading platform and exchange based in Beijing");
        return exchangeSpecification;
      case Option:
      case CoinSwap:
      case Futures:
      case PerpetualSwap:
        exchangeSpecification.setSslUri("https://api.hbdm.com");
        exchangeSpecification.setHost("api.hbdm.com");
        exchangeSpecification.setPort(80);
        exchangeSpecification.setExchangeName("Huobi");
        exchangeSpecification.setExchangeDescription(
                "Huobi is a Chinese digital currency trading platform and exchange based in Beijing");
        return exchangeSpecification;
      default:
        break;
    }
    throw new NullTradingAreaException(tradingArea);
  }

  @Override
  public SynchronizedValueFactory<Long> getNonceFactory() {
    return nonceFactory;
  }


  @Override
  public void remoteInit() throws IOException, ExchangeException {
    switch (mHexchangeSpecification.getTradingArea()){
      case Spot:
        HuobiAssetPair[] assetPairs =
                ((HuobiMarketDataServiceRaw) mHmarketDataService).getHuobiAssetPairs();
        HuobiAsset[] assets = ((HuobiMarketDataServiceRaw) mHmarketDataService).getHuobiAssets();
        exchangeMetaData = HuobiAdapters.adaptToExchangeMetaData(assetPairs, assets, exchangeMetaData);
        break;
      case Option:
      default:
        break;
    }
  }
}
