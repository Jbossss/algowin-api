package org.market.hedge.bitget.usdtswap;

import org.market.hedge.bitget.usdtswap.dto.marketdata.resp.BitgeUSwapDepthResult;
import org.market.hedge.bitget.usdtswap.dto.marketdata.resp.BitgeUSwapTicker;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

/**
 * @author chuxianbo
 *  统一设置为application/json。
 */
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public interface BitgetUSwap {

    /**
     * ：API KEY作为一个字符串。
     */
    static final String ACCESS_KEY="ACCESS-KEY";
    /**
     * 使用base64编码签名（请参阅签名消息）
     */
    static final String ACCESS_SIGN="ACCESS-SIGN";
    /**
     * 您请求的时间戳。
     */
    static final String ACCESS_TIMESTAMP ="ACCESS-TIMESTAMP";
    /**
     * 您在创建API KEY时设置的口令。
     */
    static final String ACCESS_PASSPHRASE ="ACCESS-PASSPHRASE";

    @GET
    @Path("api/swap/v3/market/depth")
    BitgeUSwapDepthResult getDepth(@QueryParam("symbol") String symbol, @QueryParam("limit") Integer limit) throws IOException;

    @GET
    @Path("api/swap/v3/market/ticker")
    BitgeUSwapTicker getTicker(@QueryParam("symbol") String symbol) throws IOException;

}
