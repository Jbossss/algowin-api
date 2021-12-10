package org.market.hedge.huobi.futures.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class HuobiFuturesContractInfo {

    String symbol;	//true	string	Product Code	"BTC","ETH"...
    String contract_code;	//true	string	Contract Code	"BTC180914" ...
    String contract_type;	//true	string	Contract Type	"this_week","next_week", "quarter" ,"next_quarter"
    BigDecimal contract_size;	//true	decimal	Contract Value (USD of one contract)	10, 100...
    BigDecimal price_tick;	//true	decimal	Minimum Variation of Contract Price	0.001, 0.01...
    String delivery_date;	//true	string	Contract Delivery Date	eg "20180720"
    String create_date;	//true	string	Contract Listing Date	eg "20180706"
    Integer contract_status;	//true	int	Contract Status	0: Delisting,1: Listing,2: Pending Listing,3: Suspension,4: Suspending of Listing,5: In Settlement,6: Delivering,7: Settlement Completed,8: Delivered,9: Suspended Listing

    public HuobiFuturesContractInfo(
            @JsonProperty("symbol") String symbol,
            @JsonProperty("contract_code") String contract_code,
            @JsonProperty("contract_type") String contract_type,
            @JsonProperty("contract_size") BigDecimal contract_size,
            @JsonProperty("price_tick") BigDecimal price_tick,
            @JsonProperty("delivery_date") String delivery_date,
            @JsonProperty("create_date") String create_date,
            @JsonProperty("contract_status") Integer contract_status) {
        this.symbol = symbol;
        this.contract_code = contract_code;
        this.contract_type = contract_type;
        this.contract_size = contract_size;
        this.price_tick = price_tick;
        this.delivery_date = delivery_date;
        this.create_date = create_date;
        this.contract_status = contract_status;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getContract_code() {
        return contract_code;
    }

    public String getContract_type() {
        return contract_type;
    }

    public BigDecimal getContract_size() {
        return contract_size;
    }

    public BigDecimal getPrice_tick() {
        return price_tick;
    }

    public String getDelivery_date() {
        return delivery_date;
    }

    public String getCreate_date() {
        return create_date;
    }

    public Integer getContract_status() {
        return contract_status;
    }
}
