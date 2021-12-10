package org.market.hedge.huobi.coinSwap;

import org.market.hedge.huobi.dto.marketdata.results.HuobiDepthResult;
import org.market.hedge.huobi.option.dto.trader.results.HuobiOptionPlaceOrdersResult;
import org.market.hedge.huobi.coinSwap.dto.trader.HuobiSwapCreateBatchOrderRequest;
import si.mazi.rescu.ParamsDigest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public interface HuobiSwap {

    @GET
    @Path("swap-ex/market/depth")
    HuobiDepthResult getDepth(@QueryParam("contract_code") String contract_code, @QueryParam("type") String type)
            throws IOException;


    @POST
    @Path("swap-api/v1/swap_batchorder")
    @Consumes(MediaType.APPLICATION_JSON)
    HuobiOptionPlaceOrdersResult placeLimitOrders(
            HuobiSwapCreateBatchOrderRequest body,
            @QueryParam("AccessKeyId") String apiKey,
            @QueryParam("SignatureMethod") String signatureMethod,
            @QueryParam("SignatureVersion") int signatureVersion,
            @QueryParam("Timestamp") String nonce,
            @QueryParam("Signature") ParamsDigest signature)
            throws IOException;


}
