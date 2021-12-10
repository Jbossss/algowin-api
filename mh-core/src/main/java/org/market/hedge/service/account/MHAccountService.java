package org.market.hedge.service.account;

import org.knowm.xchange.exceptions.NotYetImplementedForExchangeException;
import org.knowm.xchange.service.account.AccountService;
import org.market.hedge.core.BilateralPositionInfo;
import org.market.hedge.core.ParsingCurrencyPair;
import org.market.hedge.core.PositionInfo;

import java.io.IOException;

/**
 * 资产
 * Auth
 * */
public interface MHAccountService extends AccountService {

    /**
     * 获取仓位
     * */
    default PositionInfo getPosition(ParsingCurrencyPair parsingCurrencyPair, Object ...args) throws IOException {
        throw new NotYetImplementedForExchangeException();
    }

    /**
     * 获取双向仓位，可同时开空仓和多仓
     * */
    default BilateralPositionInfo getBilateralPosition(ParsingCurrencyPair parsingCurrencyPair, Object ...args) throws IOException {
        throw new NotYetImplementedForExchangeException();
    }



}