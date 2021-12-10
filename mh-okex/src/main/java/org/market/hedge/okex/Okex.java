package org.market.hedge.okex;

import org.market.hedge.okex.dto.marketdata.resp.OkexDepthResp;
import org.market.hedge.okex.dto.trade.resp.OkexCancelAllResp;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public interface Okex {


    /**
     *  @param instId 	String	是	产品ID，如 BTC-USD-190927-5000-C
     * @param sz	String	否	深度档位数量，最大值可传400，即买卖深度共800条
     * 不填写此参数，默认返回1档深度数据
     *
     * asks	Array	卖方深度
     * bids	Array	买方深度
     * ts	String	深度产生的时间
     *
     * */
    @GET
    @Path("api/v5/market/books")
    OkexDepthResp books(@QueryParam("instId") String instId, @QueryParam("sz") String sz)
            throws IOException;


    /**
     * /api/option/v3/cancel_all/<underlying>
     * 撤销全部订单
     * */
    @POST
    @Path("api/v5/market/books")
    OkexCancelAllResp cancelAll() throws IOException;

}
