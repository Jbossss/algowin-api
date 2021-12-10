package org.market.hedge.bibox.usdtswap;

import org.market.hedge.bibox.coinswap.dto.BiboxCoinSwapSingleResponse;
import org.market.hedge.bibox.usdtswap.dto.accout.resp.BiboxUSDTSwapPostionResp;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * auth api
 * */
@Path("/")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.APPLICATION_JSON)
public interface BiboxUSDTSwapAuthenticated extends BiboxUSDTSwap{

    /**
     * Create an order (market/limit)
     *
     * @return order id
     */
    @POST
    @Path("v3/cbu/order/open")
    BiboxCoinSwapSingleResponse<String> trade(
            @FormParam("cmd") String body,
            @HeaderParam(FORM_APIKEY) String apiKey,
            @HeaderParam(FORM_SIGNATURE) String signature,
            @HeaderParam(FROM_TIME) String time);

    @POST
    @Path("v3/cbu/order/closeAll")
    BiboxCoinSwapSingleResponse<String> closeAll(
            @FormParam("cmd") String body,
            @HeaderParam(FORM_APIKEY) String apiKey,
            @HeaderParam(FORM_SIGNATURE) String signature,
            @HeaderParam(FROM_TIME) String time);

    @POST
    @Path("v3/cbu/position")
    BiboxCoinSwapSingleResponse<List<BiboxUSDTSwapPostionResp>> position(
            @FormParam("cmd") String body,
            @HeaderParam(FORM_APIKEY) String apiKey,
            @HeaderParam(FORM_SIGNATURE) String signature,
            @HeaderParam(FROM_TIME) String time);


}
