package org.market.hedge.bibox.coinswap;

import org.market.hedge.bibox.coinswap.dto.BiboxCoinSwapSingleResponse;
import org.market.hedge.bibox.coinswap.dto.account.resp.BiboxCoinSwapPostionResp;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * auth api
 * */
@Path("/")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.APPLICATION_JSON)
public interface BiboxCoinSwapAuthenticated extends BiboxCoinSwap{

    /**
     * Create an order (market/limit)
     *
     * @return order id
     * @FormParam
     */
    @POST
    @Path("v3/cbc/order/open")
    BiboxCoinSwapSingleResponse<String> trade(
            @FormParam("cmd") String body,
            @HeaderParam(HEADER_API_KEY) String apiKey,
            @HeaderParam(HEADER_SIGN) String signature,
            @HeaderParam(HEADER_TIMESTAMP) String time);


    @POST
    @Path("v3/cbc/position")
    BiboxCoinSwapSingleResponse<List<BiboxCoinSwapPostionResp>> position(
            @FormParam("cmd") String body,
            @HeaderParam(FORM_APIKEY) String apiKey,
            @HeaderParam(FORM_SIGNATURE) String signature,
            @HeaderParam(FROM_TIME) String time);

    @POST
    @Path("v3/cbc/order/closeAll")
    BiboxCoinSwapSingleResponse<String> closeAll(
            @FormParam("cmd") String body,
            @HeaderParam(FORM_APIKEY) String apiKey,
            @HeaderParam(FORM_SIGNATURE) String signature,
            @HeaderParam(FROM_TIME) String time);


}
