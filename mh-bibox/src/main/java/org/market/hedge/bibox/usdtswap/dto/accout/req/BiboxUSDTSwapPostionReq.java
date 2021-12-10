package org.market.hedge.bibox.usdtswap.dto.accout.req;

public class BiboxUSDTSwapPostionReq {

    /**
     * true	string	合约符号		4BTC_USDT,4ETH_USDT, ...
     * */
    String pair;

    public BiboxUSDTSwapPostionReq(String pair) {
        this.pair = pair;
    }

    public String getPair() {
        return pair;
    }
}
