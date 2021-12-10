package org.market.hedge.binance;

import org.apache.commons.codec.binary.Hex;
import org.market.hedge.binance.option.service.BinanceOptionApiConstants;
import org.market.hedge.binance.option.service.BinanceOptionApiException;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class BinanceSignature {

    static final String op = "op";
    static final String opValue = "auth";
    private static final String signatureMethodValue = "HmacSHA256";
    public static final String signatureVersionValue = "2";

    /**
     * signature
     * */
    public static String createSignature(String accessKey, String secretKey, UrlParamsBuilder builder) {
        if (builder == null) {
            throw new BinanceOptionApiException(BinanceOptionApiException.RUNTIME_ERROR,
                    "[Invoking] Builder is null when create request with Signature");
        }
        if (accessKey == null || "".equals(accessKey) || secretKey == null || "".equals(secretKey)) {
            throw new BinanceOptionApiException(BinanceOptionApiException.KEY_MISSING, "API key and secret key are required");
        }

        builder.putToUrl("recvWindow", Long.toString(BinanceOptionApiConstants.DEFAULT_RECEIVING_WINDOW))
                .putToUrl("timestamp", Long.toString(System.currentTimeMillis()));

        Mac hmacSha256;
        try {
            hmacSha256 = Mac.getInstance(signatureMethodValue);
            SecretKeySpec secKey = new SecretKeySpec(secretKey.getBytes(), signatureMethodValue);
            hmacSha256.init(secKey);
        } catch (NoSuchAlgorithmException e) {
            throw new BinanceOptionApiException(BinanceOptionApiException.RUNTIME_ERROR,
                    "[Signature] No such algorithm: " + e.getMessage());
        } catch (InvalidKeyException e) {
            throw new BinanceOptionApiException(BinanceOptionApiException.RUNTIME_ERROR,
                    "[Signature] Invalid key: " + e.getMessage());
        }
        String payload = builder.buildSignature();
        return new String(Hex.encodeHex(hmacSha256.doFinal(payload.getBytes())));
    }

}
