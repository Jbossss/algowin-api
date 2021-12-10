package org.market.hedge.binance.perpetualSwap;

import org.market.hedge.binance.dto.BinanceException;
import org.market.hedge.binance.dto.marketdata.*;
import org.market.hedge.binance.dto.meta.BinanceSystemStatus;
import org.market.hedge.binance.dto.meta.BinanceTime;
import org.market.hedge.binance.dto.meta.exchangeinfo.BinanceExchangeInfo;
import org.market.hedge.binance.perpetualSwap.dto.marketData.resp.BinancePremiumIndex;
import org.market.hedge.binance.perpetualSwap.dto.trade.req.FundimgRatereq;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;


@Path("")
@Produces(MediaType.APPLICATION_JSON)
public interface BinancePerpetual {

  @GET
  @Path("wapi/v3/systemStatus.html")
  /**
   * Fetch system status which is normal or system maintenance.
   *
   * @throws IOException
   */
  BinanceSystemStatus systemStatus() throws IOException;

  @GET
  @Path("api/v3/ping")
  /**
   * Test connectivity to the Rest API.
   *
   * @return
   * @throws IOException
   */
  Object ping() throws IOException;

  @GET
  @Path("api/v3/time")
  /**
   * Test connectivity to the Rest API and get the current server time.
   *
   * @return
   * @throws IOException
   */
  BinanceTime time() throws IOException;

  @GET
  @Path("api/v3/exchangeInfo")
  /**
   * Current exchange trading rules and symbol information.
   *
   * @return
   * @throws IOException
   */
  BinanceExchangeInfo exchangeInfo() throws IOException;

  @GET
  @Path("fapi/v1/depth")
  /**
   * @param symbol
   * @param limit optional, default 100 max 5000. Valid limits: [5, 10, 20, 50, 100, 500, 1000,
   *     5000]
   * @return
   * @throws IOException
   * @throws BinanceException
   */
  BinanceOrderbook depth(@QueryParam("symbol") String symbol, @QueryParam("limit") Integer limit)
      throws IOException, BinanceException;

  @GET
  @Path("api/v3/aggTrades")
  /**
   * Get compressed, aggregate trades. Trades that fill at the time, from the same order, with the
   * same price will have the quantity aggregated.<br>
   * If both startTime and endTime are sent, limit should not be sent AND the distance between
   * startTime and endTime must be less than 24 hours.<br>
   * If frondId, startTime, and endTime are not sent, the most recent aggregate trades will be
   * returned.
   *
   * @param symbol
   * @param fromId optional, ID to get aggregate trades from INCLUSIVE.
   * @param startTime optional, Timestamp in ms to get aggregate trades from INCLUSIVE.
   * @param endTime optional, Timestamp in ms to get aggregate trades until INCLUSIVE.
   * @param limit optional, Default 500; max 500.
   * @return
   * @throws IOException
   * @throws BinanceException
   */
  List<BinanceAggTrades> aggTrades(
      @QueryParam("symbol") String symbol,
      @QueryParam("fromId") Long fromId,
      @QueryParam("startTime") Long startTime,
      @QueryParam("endTime") Long endTime,
      @QueryParam("limit") Integer limit)
      throws IOException, BinanceException;


  /**
   * Kline/candlestick bars for a symbol. Klines are uniquely identified by their open time.<br>
   * If startTime and endTime are not sent, the most recent klines are returned.
   *
   * @param symbol
   * @param interval
   * @param limit optional, default 500; max 500.
   * @param startTime optional
   * @param endTime optional
   * @return
   * @throws IOException
   * @throws BinanceException
   */
  @GET
  @Path("fapi/v1/klines")
  List<Object[]> klines(
      @QueryParam("symbol") String symbol,
      @QueryParam("interval") String interval,
      @QueryParam("limit") Integer limit,
      @QueryParam("startTime") Long startTime,
      @QueryParam("endTime") Long endTime)
      throws IOException, BinanceException;

  @GET
  @Path("api/v3/ticker/24hr")
  /**
   * 24 hour price change statistics for all symbols. - bee carreful this api call have a big
   * weight, only about 4 call per minut can be without ban.
   *
   * @return
   * @throws IOException
   * @throws BinanceException
   */
  List<BinanceTicker24h> ticker24h() throws IOException, BinanceException;

  @GET
  @Path("api/v3/ticker/24hr")
  /**
   * 24 hour price change statistics.
   *
   * @param symbol
   * @return
   * @throws IOException
   * @throws BinanceException
   */
  BinanceTicker24h ticker24h(@QueryParam("symbol") String symbol)
      throws IOException, BinanceException;

  @GET
  @Path("api/v3/ticker/price")
  /**
   * Latest price for a symbol.
   *
   * @return
   * @throws IOException
   * @throws BinanceException
   */
  BinancePrice tickerPrice(@QueryParam("symbol") String symbol)
      throws IOException, BinanceException;

  @GET
  @Path("api/v3/ticker/price")
  /**
   * Latest price for all symbols.
   *
   * @return
   * @throws IOException
   * @throws BinanceException
   */
  List<BinancePrice> tickerAllPrices() throws IOException, BinanceException;

  @GET
  @Path("api/v3/ticker/bookTicker")
  /**
   * Best price/qty on the order book for all symbols.
   *
   * @return
   * @throws IOException
   * @throws BinanceException
   */
  List<BinancePriceQuantity> tickerAllBookTickers() throws IOException, BinanceException;


  @GET
  @Path("fapi/v1/premiumIndex")
  /**
   * 最新标记价格和资金费率
   */
  BinancePremiumIndex getPremiumIndex(@QueryParam("symbol") String symbol)
          throws IOException, BinanceException;


  @GET
  @Path("fapi/v1/premiumIndex")
  /**
   * 最新标记价格和资金费率()所有
   */
  List<BinancePremiumIndex> getAllPremiumIndex()
          throws IOException, BinanceException;

  @GET
  @Path("fapi/v1/fundingRate")
  /**
   * 查询资金费率历史
   */
  List<FundimgRatereq> getFundingRate(@QueryParam("symbol") String symbol,
                                      @QueryParam("startTime") Long startTime,
                                      @QueryParam("endTime") Long endTime,
                                      @QueryParam("limit") Integer limit)
          throws IOException, BinanceException;


}