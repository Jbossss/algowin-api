package org.market.hedge.service;

import org.knowm.xchange.currency.CurrencyPair;
import org.market.hedge.core.ParsingCurrencyPair;

/**
 * 货币对解析排列，返回交易所可用的货币对
 * */
public interface StreamingParsingCurrencyPair {

    /**
     * 解析
     * */
    ParsingCurrencyPair parsing(CurrencyPair currencyPair, Object... args);



}
