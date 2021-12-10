package org.market.hedge.huobi.futures.dto.trader;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class HuobiFuturesCreateBatchOrderRequest {

    @JsonProperty("orders_data")
    public List<HuobiFuturesCreateOrderRequest> ordersData;

    public HuobiFuturesCreateBatchOrderRequest(
            List<HuobiFuturesCreateOrderRequest> ordersData
    ) {
        this.ordersData = ordersData;
    }

    public List<HuobiFuturesCreateOrderRequest> getOrdersData() {
        return ordersData;
    }

}
