package org.market.hedge.bibox.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import javax.ws.rs.FormParam;

import com.sun.crypto.provider.HmacMD5;
import org.apache.commons.codec.digest.HmacUtils;
import org.knowm.xchange.utils.DigestUtils;
import org.market.hedge.bibox.BiboxAuthenticated;
import org.knowm.xchange.service.BaseParamsDigest;
import org.market.hedge.bibox.coinswap.util.Hex;
import org.market.hedge.bibox.coinswap.util.MacUtils;
import si.mazi.rescu.RestInvocation;

public class BiboxDigest extends BaseParamsDigest {


  public static String buildSignature(String parms,String secretKey) {
    return Hex.encodeHexString(
            MacUtils.buildMAC(parms, "HmacMD5", secretKey)).toLowerCase();
  }


  public static String sign(String parms,String secretKey) {
    return HmacUtils.hmacMd5Hex(secretKey, parms);
  }

  /**
   * Constructor
   *
   * @param secretKey
   * @throws IllegalArgumentException if key is invalid (cannot be base-64-decoded or the decoded
   *     key is invalid).
   */
  private BiboxDigest(String secretKey) {

    super(secretKey, HMAC_MD5);
  }

  public static BiboxDigest createInstance(String secretKey) {

    return secretKey == null ? null : new BiboxDigest(secretKey);
  }

  @Override
  public String digestParams(RestInvocation restInvocation) {

    String cmds =
        (String) restInvocation.getParamValue(FormParam.class, BiboxAuthenticated.FORM_CMDS);
    try {
      return DigestUtils.bytesToHex(getMac().doFinal(cmds.getBytes("UTF-8"))).toLowerCase();
    } catch (IllegalStateException | UnsupportedEncodingException e1) {
      throw new RuntimeException(e1.getMessage());
    }
  }
}
