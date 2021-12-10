package org.market.hedge.huobi.futures;

import org.market.hedge.huobi.dto.marketdata.results.HuobiDepthResult;
import org.market.hedge.huobi.futures.dto.account.HuobiFuturesPositionInfoRequest;
import org.market.hedge.huobi.futures.dto.account.results.HuobiFuturesPositionInfoResult;
import org.market.hedge.huobi.futures.dto.marketdata.results.HuobiFuturesContractInfoResult;
import org.market.hedge.huobi.futures.dto.trader.HuobiFuturesCreateBatchOrderRequest;
import org.market.hedge.huobi.option.dto.trader.results.HuobiOptionPlaceOrdersResult;
import si.mazi.rescu.ParamsDigest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public interface HuobiFutures {

    @GET
    @Path("market/depth")
    HuobiDepthResult getDepth(@QueryParam("symbol") String symbol, @QueryParam("type") String type)
            throws IOException;

    /**
     * @param symbol symbol	string	false	Case-Insenstive.Both uppercase and lowercase are supported.e.g. "BTC","ETH"...
     * @param contract_type contract_type	string	false	"this_week","next_week", "quarter" "next_quarter"
     * @param contract_code contract_code	string	false	BTC180914
     * */
    @GET
    @Path("api/v1/contract_contract_info")
    HuobiFuturesContractInfoResult getContractInfos(
            @QueryParam("symbol") String symbol,
            @QueryParam("contract_type") String contract_type,
            @QueryParam("contract_code") String contract_code)throws IOException;


    @POST
    @Path("api/v1/contract_batchorder")
    @Consumes(MediaType.APPLICATION_JSON)
    HuobiOptionPlaceOrdersResult placeLimitOrders(
            HuobiFuturesCreateBatchOrderRequest body,
            @QueryParam("AccessKeyId") String apiKey,
            @QueryParam("SignatureMethod") String signatureMethod,
            @QueryParam("SignatureVersion") int signatureVersion,
            @QueryParam("Timestamp") String nonce,
            @QueryParam("Signature") ParamsDigest signature)
            throws IOException;

    @POST
    @Path("api/v1/contract_position_info")
    @Consumes(MediaType.APPLICATION_JSON)
    HuobiFuturesPositionInfoResult contractPositionInfo(
            HuobiFuturesPositionInfoRequest body,
            @QueryParam("AccessKeyId") String apiKey,
            @QueryParam("SignatureMethod") String signatureMethod,
            @QueryParam("SignatureVersion") int signatureVersion,
            @QueryParam("Timestamp") String nonce,
            @QueryParam("Signature") ParamsDigest signature
    )throws IOException;


}
