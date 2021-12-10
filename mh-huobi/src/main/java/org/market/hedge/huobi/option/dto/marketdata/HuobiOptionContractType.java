package org.market.hedge.huobi.option.dto.marketdata;

public enum  HuobiOptionContractType {

    this_week("this_week"), //当周

    next_week("next_week"), //次周

    quarter("quarter"); //季度

    private String value;

    public String getValue() {
        return value;
    }

    HuobiOptionContractType(String value){
        this.value=value;
    }


}
