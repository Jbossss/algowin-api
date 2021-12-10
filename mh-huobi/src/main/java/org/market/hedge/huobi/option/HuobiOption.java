package org.market.hedge.huobi.option;

import org.market.hedge.huobi.dto.marketdata.results.HuobiDepthResult;
import org.market.hedge.huobi.option.dto.HuobiOptionContractCodeRequest;
import org.market.hedge.huobi.option.dto.HuobiOptionCreateOrderRequest;
import org.market.hedge.huobi.option.dto.HuobiOptionCreateOrdersRequest;
import org.market.hedge.huobi.option.dto.account.HuobiOptionPositionInfoRequest;
import org.market.hedge.huobi.option.dto.account.results.HuobiOptionPositionInfoResults;
import org.market.hedge.huobi.option.dto.marketdata.results.HuobiOptionContractInfoResult;
import org.market.hedge.huobi.option.dto.account.results.HuobiOptionPositionsResults;
import org.market.hedge.huobi.option.dto.trader.HuobiOptionTransactionHistoryRequest;
import org.market.hedge.huobi.option.dto.trader.results.HuobiOptionCancelAllResult;
import org.market.hedge.huobi.option.dto.trader.results.HuobiOptionPlaceOrderResult;
import org.market.hedge.huobi.option.dto.trader.results.HuobiOptionPlaceOrdersResult;
import org.market.hedge.huobi.option.dto.trader.results.HuobiOptionTransactionHistoryResult;
import si.mazi.rescu.ParamsDigest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public interface HuobiOption {

    @GET
    @Path("option-ex/market/depth")
    HuobiDepthResult getDepth(@QueryParam("contract_code") String contract_code, @QueryParam("type") String type)
            throws IOException;


    @GET
    @Path("option-api/v1/option_contract_info")
    @Consumes(MediaType.APPLICATION_JSON)
    HuobiOptionContractInfoResult getOptionContractInfo(
            @QueryParam("symbol")
                    String symbol,	//false		品种代码	"BTC","ETH"，如果缺省，默认返回所有品种
            @QueryParam("trade_partition")
                    String trade_partition,	//	false		交易分区，不填默认”USDT“	"USDT"
            @QueryParam("contract_type")
                    String contract_type,	//	false		合约类型	当周:"this_week", 次周:"next_week", 季度:"quarter"
            @QueryParam("contract_code")
                    String contract_code)	//	false		合约代码	BTC-USDT-200508-C-8800)
            throws IOException;


    @POST
    @Path("option-api/v1/option_order")
    @Consumes(MediaType.APPLICATION_JSON)
    HuobiOptionPlaceOrderResult placeLimitOrder(
            HuobiOptionCreateOrderRequest body,
            @QueryParam("AccessKeyId") String apiKey,
            @QueryParam("SignatureMethod") String signatureMethod,
            @QueryParam("SignatureVersion") int signatureVersion,
            @QueryParam("Timestamp") String nonce,
            @QueryParam("Signature") ParamsDigest signature)
            throws IOException;

    @POST
    @Path("option-api/v1/option_batchorder")
    @Consumes(MediaType.APPLICATION_JSON)
    HuobiOptionPlaceOrdersResult placeLimitOrders(
            HuobiOptionCreateOrdersRequest body,
            @QueryParam("AccessKeyId") String apiKey,
            @QueryParam("SignatureMethod") String signatureMethod,
            @QueryParam("SignatureVersion") int signatureVersion,
            @QueryParam("Timestamp") String nonce,
            @QueryParam("Signature") ParamsDigest signature)
            throws IOException;


    @POST
    @Path("option-api/v1/option_position_info")
    @Consumes(MediaType.APPLICATION_JSON)
    HuobiOptionPositionsResults getPosition(
            HuobiOptionContractCodeRequest body,
            @QueryParam("AccessKeyId") String apiKey,
            @QueryParam("SignatureMethod") String signatureMethod,
            @QueryParam("SignatureVersion") int signatureVersion,
            @QueryParam("Timestamp") String nonce,
            @QueryParam("Signature") ParamsDigest signature)
            throws IOException;


    @POST
    @Path("option-api/v1/option_position_limit")
    @Consumes(MediaType.APPLICATION_JSON)
    HuobiOptionPositionInfoResults option_position_limit(
            HuobiOptionPositionInfoRequest body,
            @QueryParam("AccessKeyId") String apiKey,
            @QueryParam("SignatureMethod") String signatureMethod,
            @QueryParam("SignatureVersion") int signatureVersion,
            @QueryParam("Timestamp") String nonce,
            @QueryParam("Signature") ParamsDigest signature)
            throws IOException;

    @POST
    @Path("option-api/v1/option_cancelall")
    @Consumes(MediaType.APPLICATION_JSON)
    HuobiOptionCancelAllResult cancelAll(
            HuobiOptionContractCodeRequest body,
            @QueryParam("AccessKeyId") String apiKey,
            @QueryParam("SignatureMethod") String signatureMethod,
            @QueryParam("SignatureVersion") int signatureVersion,
            @QueryParam("Timestamp") String nonce,
            @QueryParam("Signature") ParamsDigest signature)
            throws IOException;




    @POST
    @Path("option-api/v1/option_matchresults")
    @Consumes(MediaType.APPLICATION_JSON)
    HuobiOptionTransactionHistoryResult getTransactionHistory(
            HuobiOptionTransactionHistoryRequest body,
            @QueryParam("AccessKeyId") String apiKey,
            @QueryParam("SignatureMethod") String signatureMethod,
            @QueryParam("SignatureVersion") int signatureVersion,
            @QueryParam("Timestamp") String nonce,
            @QueryParam("Signature") ParamsDigest signature)
            throws IOException;

}
