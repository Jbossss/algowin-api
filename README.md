# AlgoWin Api

[toc]

## 简介

**基于[XChange](http://knowm.org/open-source/xchange)编写**

XChange 是一个Java library，与60多个数字货币交易所api进行对接，但紧紧只实现了个个交易所spot api 对接，AlgoWin Api 在此升级，实现了部分交易所的币本位,U本位永续,和期权,期货。

XChange github :https://github.com/knowm/XChange

XChange 官网 :https://knowm.org/open-source/xchange/



## 支持哪些？

排除XChange接入spot已经全部实现外，大部分交易类型实现的Api具体要down下来看实现类，不过没有实现的很容易改造接入

**public MarketData：**

+ Get OrderBook

+ Get Depth
+ Get Ticker
+ Get Option contract info

**private Account**

+ Get  Position
+ Get  Position list

**private Trader**

+ Open Limit Order
+ Open Market Order
+ Cancel by Order
+ Cancel all by instrument

+ Get Transaction History

| xchange | Spot | COIN-M Swap | USDT-M Swap | COIN-M Futures | Options |
| ------- | ---- | ----------- | ----------- | -------------- | ------- |
| Binance | ✔️    | ✔️           | ✔️           | ✔️              | ✔️       |
| Bibox   | ✔️    | ✔️           | ✔️           | ❌              | ❌       |
| Bitget  | ✔️    | ❌           | ✔️           | ❌              | ❌       |
| Deribit | ❌    | ❌           | ✔️           | ❌              | ✔️       |
| Huobi   | ✔️    | ✔️           | ✔️           | ✔️              | ✔️       |
| Okex    | ✔️    | ❌           | ✔️           | ❌              | ✔️       |
| 58Coin  |      |             |             |                |         |



## REST API

### 初始化定义

**初始化需要先定义交易所，和交易类型 TradingArea eg:**

```
        MHExchange huobi= MHExchangeFactory.INSTANCE.createExchange(HuobiExchange.class, TradingArea.Option);
```

**or :**

```
        MHExchange huobi= MHExchangeFactory.INSTANCE.createExchange(BinanceExchange.class, TradingArea.Futures);
```

**其次定义解析每家交易所的合约名称解析类：**

```
StreamingParsingCurrencyPair parsing=huobi.getStreamingParsing().parsingCurrencyPair;
```

通过 `CurrencyPair pair = new CurrencyPair("EKO","BTC"); parsing.parsing(pair)`既可解析出这家交易所api所需要的合约名称 各家交易所大同小异 eg: 4BTC_USDT , BTC_USDT , btcusdt , btcswap .....



#### Public Market Data

not require authentication:

```
MHExchange huobi= MHExchangeFactory.INSTANCE.createExchange(HuobiExchange.class, TradingArea.Spot);
        StreamingParsingCurrencyPair parsing=huobi.getStreamingParsing().parsingCurrencyPair;
        MHMarketDataService marketDataService=huobi.getMarketDataService();
marketDataService.getOrderBook(.....)
```

#### Private Account Info

To use APIs which require authentication

```
MHExchange huobi= MHExchangeFactory.INSTANCE.createExchange(HuobiExchange.class
                ,"你的 api key"
                ,"你的 secret key"
                , TradingArea.Spot);
StreamingParsingCurrencyPair parsing=huobi.getStreamingParsing().parsingCurrencyPair;
MHAccountService accountService=huobi.getAccountService();
accountService.getPosition(.....)
```

#### Private Trader

To use APIs which require authentication

```
        MHExchange huobi= MHExchangeFactory.INSTANCE.createExchange(HuobiExchange.class
                ,"91fc3ce3-nbtycf4rw2-e21765a3-c6869"
                ,"42cdc46e-9be105f5-6047f5a8-464a2"
                , TradingArea.Spot);
        StreamingParsingCurrencyPair parsing=huobi.getStreamingParsing().parsingCurrencyPair;
        MHTradeService tradeService=  huobi.getTradeService();
        tradeService.placeMarketOrder(.........);
```




