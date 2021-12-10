package org.market.hedge.bitget.usdtswap.service;

import org.knowm.xchange.service.BaseParamsDigest;
import si.mazi.rescu.RestInvocation;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

import static org.knowm.xchange.utils.DigestUtils.bytesToHex;

public class BitgetUSwapDigest extends BaseParamsDigest {

  final static Base64.Encoder encoder = Base64.getEncoder();

  protected BitgetUSwapDigest(String secretKeyBase64, String hmacString) throws IllegalArgumentException {
    super(secretKeyBase64, hmacString);
  }

  @Override
  public String digestParams(RestInvocation restInvocation) {
    return null;
  }

  /**
   * queryString为空时，签名格式
   *
   * timestamp + method.toUpperCase() + requestPath + body
   *
   * queryString不为空时，签名格式
   *
   * timestamp + method.toUpperCase() + requestPath + "?" + queryString + body
   *
   * eg:'1561022985382POST/api/swap/v3/order/placeOrder{"symbol":"cmt_btcusdt","size":"8","type":"1","match_price":"1","order_type":"1","client_oid":"bitget#123456"}'
   * */
  public static String buildSignature(String secretKey,long timestamp,String method,String requestPath,String queryString,String body) throws Exception {
    String message=null;
    if (queryString.isEmpty()){
      message=timestamp + method.toUpperCase() + requestPath + body;
    }else {
      message=timestamp + method.toUpperCase() + requestPath + "?" + queryString + body;
    }
    Mac sha256Mac = Mac.getInstance("HmacSHA256");
    SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "HmacSHA256");
    sha256Mac.init(secretKeySpec);
    String hashHex = bytesToHex(sha256Mac.doFinal(message.getBytes())).toLowerCase();
    final byte[] textByte = hashHex.getBytes("UTF-8");
    return encoder.encodeToString(textByte);
  }



}
