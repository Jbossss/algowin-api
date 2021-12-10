package org.market.hedge.bibox.coinswap.service;

import org.knowm.xchange.service.BaseParamsDigest;
import org.market.hedge.bibox.coinswap.util.Hex;
import org.market.hedge.bibox.coinswap.util.MacUtils;
import si.mazi.rescu.RestInvocation;

public class BiboxCoinSwapDigest extends BaseParamsDigest {

    protected BiboxCoinSwapDigest(String secretKeyBase64, String hmacString) throws IllegalArgumentException {
        super(secretKeyBase64, hmacString);
    }

    public static String buildSignature(String text, String secretKey) {
        return Hex.encodeHexString(
                MacUtils.buildMAC(text, "HmacMD5", secretKey)).toLowerCase();
    }


    @Override
    public String digestParams(RestInvocation restInvocation) {
        return null;
    }


}
