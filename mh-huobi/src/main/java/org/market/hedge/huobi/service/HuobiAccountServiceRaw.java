package org.market.hedge.huobi.service;

import org.knowm.xchange.Exchange;
import org.market.hedge.huobi.HuobiUtils;
import org.market.hedge.huobi.dto.account.results.*;
import org.market.hedge.huobi.dto.account.results.*;
import org.market.hedge.huobi.dto.account.*;

import java.io.IOException;
import java.math.BigDecimal;

public class HuobiAccountServiceRaw extends HuobiBaseService {
  private HuobiAccount[] accountCache = null;

  HuobiAccountServiceRaw(Exchange exchange) {
    super(exchange);
  }

  HuobiBalance getHuobiBalance(String accountID) throws IOException {
    HuobiBalanceResult huobiBalanceResult =
        huobi.getBalance(
            accountID,
            exchange.getExchangeSpecification().getApiKey(),
            HuobiDigest.HMAC_SHA_256,
            2,
            HuobiUtils.createUTCDate(exchange.getNonceFactory()),
            signatureCreator);
    return checkResult(huobiBalanceResult);
  }

  public HuobiAccount[] getAccounts() throws IOException {
    if (accountCache == null) {
      HuobiAccountResult huobiAccountResult =
          huobi.getAccount(
              exchange.getExchangeSpecification().getApiKey(),
              HuobiDigest.HMAC_SHA_256,
              2,
              HuobiUtils.createUTCDate(exchange.getNonceFactory()),
              signatureCreator);
      accountCache = checkResult(huobiAccountResult);
    }

    return accountCache;
  }

  public HuobiFeeRate[] getFeeRate(String symbols) throws IOException {
    HuobiFeeRateResult transactFeeRateResult =
        huobi.getFeeRate(
            symbols.toLowerCase(),
            exchange.getExchangeSpecification().getApiKey(),
            HuobiDigest.HMAC_SHA_256,
            2,
            HuobiUtils.createUTCDate(exchange.getNonceFactory()),
            signatureCreator);
    return checkResult(transactFeeRateResult);
  }

  public HuobiTransactFeeRate[] getTransactFeeRate(String currency) throws IOException {
    HuobiTransactFeeRateResult transactFeeRateResult =
        huobi.getTransactFeeRate(
            currency.toLowerCase(),
            exchange.getExchangeSpecification().getApiKey(),
            HuobiDigest.HMAC_SHA_256,
            2,
            HuobiUtils.createUTCDate(exchange.getNonceFactory()),
            signatureCreator);
    return checkResult(transactFeeRateResult);
  }

  public String getDepositAddress(String currency) throws IOException {
    HuobiDepositAddressResult depositAddressResult =
        huobi.getDepositAddress(
            currency.toLowerCase(),
            exchange.getExchangeSpecification().getApiKey(),
            HuobiDigest.HMAC_SHA_256,
            2,
            HuobiUtils.createUTCDate(exchange.getNonceFactory()),
            signatureCreator);
    return checkResult(depositAddressResult);
  }

  public HuobiDepositAddress[] getDepositAddressV2(String currency) throws IOException {
    HuobiDepositAddressV2Result depositAddressResult =
        huobi.getDepositAddressV2(
            currency != null ? currency.toLowerCase() : null,
            exchange.getExchangeSpecification().getApiKey(),
            HuobiDigest.HMAC_SHA_256,
            2,
            HuobiUtils.createUTCDate(exchange.getNonceFactory()),
            signatureCreator);
    return checkResult(depositAddressResult);
  }

  public HuobiDepositAddressWithTag getDepositAddressWithTag(String currency) throws IOException {
    HuobiDepositAddressWithTagResult depositAddressWithTagResult =
        huobi.getDepositAddressWithTag(
            currency.toLowerCase(),
            exchange.getExchangeSpecification().getApiKey(),
            HuobiDigest.HMAC_SHA_256,
            2,
            HuobiUtils.createUTCDate(exchange.getNonceFactory()),
            signatureCreator);
    return checkResult(depositAddressWithTagResult);
  }

  public HuobiFundingRecord[] getDepositWithdrawalHistory(String currency, String type, String from)
      throws IOException {
    HuobiFundingHistoryResult fundingHistoryResult =
        huobi.getFundingHistory(
            currency != null ? currency.toLowerCase() : null,
            type.toLowerCase(),
            from,
            "100",
            exchange.getExchangeSpecification().getApiKey(),
            HuobiDigest.HMAC_SHA_256,
            2,
            HuobiUtils.createUTCDate(exchange.getNonceFactory()),
            signatureCreator);
    return checkResult(fundingHistoryResult);
  }

  public HuobiWithdrawFeeRange getWithdrawFeeRange(String currency) throws IOException {
    HuobiWithdrawFeeRangeResult result =
        huobi.getWithdrawFeeRange(
            currency.toLowerCase(),
            exchange.getExchangeSpecification().getApiKey(),
            HuobiDigest.HMAC_SHA_256,
            2,
            HuobiUtils.createUTCDate(exchange.getNonceFactory()),
            signatureCreator);
    return checkResult(result);
  }

  public long createWithdraw(
      String currency, BigDecimal amount, BigDecimal fee, String address, String addressTag)
      throws IOException {
    HuobiCreateWithdrawRequest createWithdrawRequest =
        new HuobiCreateWithdrawRequest(address, amount, currency.toLowerCase(), fee, addressTag);
    HuobiCreateWithdrawResult createWithdrawResult =
        huobi.createWithdraw(
            createWithdrawRequest,
            exchange.getExchangeSpecification().getApiKey(),
            HuobiDigest.HMAC_SHA_256,
            2,
            HuobiUtils.createUTCDate(exchange.getNonceFactory()),
            signatureCreator);
    return checkResult(createWithdrawResult);
  }
}
