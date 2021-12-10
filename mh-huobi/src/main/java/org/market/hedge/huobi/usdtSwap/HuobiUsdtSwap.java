package org.market.hedge.huobi.usdtSwap;

import org.market.hedge.huobi.dto.marketdata.results.HuobiDepthResult;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public interface HuobiUsdtSwap {

    @GET
    @Path("linear-swap-ex/market/depth")
    HuobiDepthResult getDepth(@QueryParam("contract_code") String contract_code, @QueryParam("type") String type)
            throws IOException;


}
