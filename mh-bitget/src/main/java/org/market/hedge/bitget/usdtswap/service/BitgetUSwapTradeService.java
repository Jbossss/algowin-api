package org.market.hedge.bitget.usdtswap.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.exceptions.ExchangeException;
import org.market.hedge.bitget.usdtswap.dto.trade.req.BitgetUsdtSwapOrderDataListReq;
import org.market.hedge.bitget.usdtswap.dto.trade.req.BitgetUsdtSwapOrderReq;
import org.market.hedge.bitget.usdtswap.dto.trade.resp.BitgetUsdtSwapSingleResponse;
import org.market.hedge.core.ParsingCurrencyPair;
import org.market.hedge.dto.trade.MHLimitOrder;
import org.market.hedge.service.trade.MHTradeService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/** @author odrotleff */
public class BitgetUSwapTradeService extends BitgetUSwapTradeServiceRaw implements MHTradeService {

  public BitgetUSwapTradeService(Exchange exchange) {
    super(exchange);
  }

  public int num = 0;

  @Override
  public List<String> placeLimitOrders(List<MHLimitOrder> limitOrders) throws IOException {
    List<BitgetUsdtSwapOrderDataListReq> ordersData=new ArrayList<>();
     num = 0;
    for (MHLimitOrder LimitOrder:limitOrders){
      ordersData.add(getBitgetUsdtSwapCreateOrderRequest(LimitOrder));
    }
    long timestamp = System.currentTimeMillis();
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      BitgetUsdtSwapSingleResponse result =
              bitget.trade(
                      new BitgetUsdtSwapOrderReq(limitOrders.get(0).getParsingCurrencyPair().getParsing(),ordersData),
                      exchange.getExchangeSpecification().getApiKey(),
                      BitgetUSwapDigest.buildSignature(exchange.getExchangeSpecification().getSecretKey(),timestamp,
                              "post",
                              " /api/swap/v3/order/batchOrders",
                              "",
                              objectMapper.writeValueAsString(ordersData)
                      ),
                      timestamp,
                      "");
    }catch (Exception e){
      e.getMessage();
    }
    return null;
  }

  @Override
  public void cancelAllByInstrument(ParsingCurrencyPair parsingCurrencyPair) throws IOException {

  }

  public BitgetUsdtSwapOrderDataListReq getBitgetUsdtSwapCreateOrderRequest(MHLimitOrder LimitOrder){
    String type,offset;
    if (LimitOrder.getType() == Order.OrderType.BID) {
      type = "1";
      offset="open";
    } else if (LimitOrder.getType() == Order.OrderType.ASK) {
      type = "2";
      offset="open";
    } else if (LimitOrder.getType() == Order.OrderType.EXIT_BID) {
      type = "3";
      offset="close";
    } else if (LimitOrder.getType() == Order.OrderType.EXIT_ASK) {
      type = "4";
      offset="close";
    } else {
      throw new ExchangeException("Unsupported order type.");
    }

    return new BitgetUsdtSwapOrderDataListReq(
            LimitOrder.getLimitPrice().toString(),
            LimitOrder.getOriginalAmount().toString(),
            type,
            "0",
            "0",
           "wangjun#"+num++
     );
  }
}
