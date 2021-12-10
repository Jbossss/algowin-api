package org.market.hedge.bibox.usdtswap.service;

import com.alibaba.fastjson.JSONObject;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.exceptions.ExchangeException;
import org.market.hedge.bibox.BiboxException;
import org.market.hedge.bibox.coinswap.dto.BiboxCoinSwapSingleResponse;
import org.market.hedge.bibox.coinswap.dto.account.resp.BiboxCoinSwapPostionResp;
import org.market.hedge.bibox.coinswap.service.BiboxCoinSwapDigest;
import org.market.hedge.bibox.usdtswap.dto.accout.resp.BiboxUSDTSwapPostionResp;
import org.market.hedge.core.BilateralPositionInfo;
import org.market.hedge.core.ParsingCurrencyPair;
import org.market.hedge.core.PositionInfo;
import org.market.hedge.service.account.MHAccountService;

import java.io.IOException;
import java.util.List;

/** @author odrotleff */
public class BiboxUSDTSwapAccountService extends BiboxUSDTSwapAccountServiceRaw implements MHAccountService {

  public BiboxUSDTSwapAccountService(Exchange exchange) {
    super(exchange);
  }

  @Override
  public BilateralPositionInfo getBilateralPosition(ParsingCurrencyPair parsingCurrencyPair, Object... args) throws IOException {
    try {

      JSONObject json = new JSONObject();
      json.put("pair", parsingCurrencyPair.getParsing());

      String text=json.toJSONString();
      long timestamp = System.currentTimeMillis();
      String sign = BiboxCoinSwapDigest.buildSignature(timestamp + text,exchange.getExchangeSpecification().getSecretKey());
      BiboxCoinSwapSingleResponse<List<BiboxUSDTSwapPostionResp>> response = bibox.position(text,apiKey,sign,String.valueOf(timestamp));
      throwErrors(response);

      List<BiboxUSDTSwapPostionResp> list = response.getResult();
      PositionInfo bidPosition=null;
      PositionInfo askPosition=null;
      for (int i=0;i<list.size();i++){
        //仓位方向: 1, 多仓 2 , 空仓
        if (list.get(i).getSd()==1){
          BiboxUSDTSwapPostionResp bid = list.get(i);
          bidPosition=PositionInfo.builder()
                  .addAvailable(bid.getLc())
                  .addContract_code(bid.getPi())
                  .addCost_hold(null)
                  .addCost_open(null)
                  .addDirection(bid.getSd()==1?"buy":"sell")
                  .addFrozen(null)
                  .addLever_rate(null)
                  .addPosition_margin(null)
                  .addProfit(null)
                  .addProfit_rate(null)
                  .addProfit_unreal(null)
                  .addSymbol(null)
                  .addVolume(bid.getHc())
                  .build();

        }else if (list.get(i).getSd()==2){
          BiboxUSDTSwapPostionResp ask = list.get(i);
          askPosition=PositionInfo.builder()
                  .addAvailable(ask.getLc())
                  .addContract_code(ask.getPi())
                  .addCost_hold(null)
                  .addCost_open(null)
                  .addDirection(ask.getSd()==1?"buy":"sell")
                  .addFrozen(null)
                  .addLever_rate(null)
                  .addPosition_margin(null)
                  .addProfit(null)
                  .addProfit_rate(null)
                  .addProfit_unreal(null)
                  .addSymbol(null)
                  .addVolume(ask.getHc())
                  .build();
        }
      }


      return new BilateralPositionInfo(bidPosition,askPosition);
    } catch (BiboxException e) {
      throw new ExchangeException(e.getMessage());
    }
  }

}
