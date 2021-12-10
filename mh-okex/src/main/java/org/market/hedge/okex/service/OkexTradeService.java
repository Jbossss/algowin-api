package org.market.hedge.okex.service;

import com.alibaba.fastjson.JSONObject;
import com.okex.open.api.bean.trade.param.CancelOrder;
import com.okex.open.api.bean.trade.param.PlaceOrder;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.service.trade.params.CancelOrderParams;
import org.market.hedge.core.ParsingCurrencyPair;
import org.market.hedge.dto.trade.MHLimitOrder;
import org.market.hedge.okex.dto.trade.resp.OkexBatchOrdersResp;
import org.market.hedge.okex.dto.trade.resp.OkexOrder;
import org.market.hedge.service.trade.MHTradeService;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OkexTradeService extends OkexTradeServiceRaw implements MHTradeService {

    public OkexTradeService(Exchange exchange) {
        super(exchange);
    }

    @Override
    public String placeLimitOrder(MHLimitOrder limitOrder) throws IOException {
        List<PlaceOrder> placeOrders =new ArrayList<>();

        PlaceOrder placeOrder =new PlaceOrder();
        placeOrder.setInstId("BTC-USD-211008-40000-C");
        placeOrder.setTdMode("cross");
        placeOrder.setCcy("USDT");
        //placeOrder.setClOrdId("0423a3a06");
//        placeOrder.setTag("");
        placeOrder.setSide("buy");
        //placeOrder.setPosSide("short");
        placeOrder.setOrdType("limit");
        placeOrder.setSz("1");
        placeOrder.setPx("0.01");
//        placeOrder.setReduceOnly(false);
        JSONObject result = tradeAPIService.placeOrder(placeOrder);
        return result.toJSONString();
    }

    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dateString = sdf.format(new Date());
        System.out.println(dateString.substring(2,dateString.length()));
    }

    @Override
    public List<String> placeLimitOrders(List<MHLimitOrder> limitOrders) throws IOException {
        List<PlaceOrder> placeOrders =new ArrayList<>();
        limitOrders.forEach(e->{
            PlaceOrder placeOrder1 =new PlaceOrder();
            placeOrder1.setInstId(e.getParsingCurrencyPair().getParsing());
            placeOrder1.setTdMode("isolated");
            //placeOrder1.setClOrdId("12);
            //placeOrder1.setTag("");
            String side = null;
            String posSide = null;
            switch(e.getType()){
                case ASK:
                case EXIT_ASK:
                    side="sell";
                    break;
                case BID:
                case EXIT_BID:
                    side="buy";
                    break;
                default:
                    break;
            }
            /*switch(e.getType()){
                case EXIT_ASK:
                    posSide="short";
                case EXIT_BID:
                    posSide="long";
                    break;
                default:
                    break;
            }*/
            placeOrder1.setSide(side);
            placeOrder1.setPosSide(posSide);
            placeOrder1.setOrdType("limit");
            placeOrder1.setSz(e.getOriginalAmount().toPlainString());
            placeOrder1.setPx(e.getLimitPrice().toPlainString());
            placeOrder1.setReduceOnly(null);
            placeOrders.add(placeOrder1);
        });

        JSONObject resultJson = tradeAPIService.placeMultipleOrders(placeOrders);
        List<String> relis=new ArrayList<>();
        int code=Integer.parseInt(resultJson.getString("code"));
        if (code!=0){
            throw new ExchangeException("code:"+code+" msg:"+resultJson.getString("msg"));
        }
        for (Object data : resultJson.getJSONArray("data")) {
            JSONObject order=(JSONObject)data;
            relis.add(order.getString("ordId"));
        }
        return relis;
    }

    @Override
    public void cancelAllByInstrument(ParsingCurrencyPair parsingCurrencyPair) throws IOException {
        List<CancelOrder> cancelOrders =new ArrayList<>();
        CancelOrder cancelOrder1 = new CancelOrder();
        cancelOrder1.setInstId(parsingCurrencyPair.getParsing());
        cancelOrders.add(cancelOrder1);
        JSONObject result = tradeAPIService.cancelMultipleOrders(cancelOrders);
    }


    @Override
    public boolean cancelOrder(CancelOrderParams orderParams) throws IOException {
        return MHTradeService.super.cancelOrder(orderParams);
    }
}

