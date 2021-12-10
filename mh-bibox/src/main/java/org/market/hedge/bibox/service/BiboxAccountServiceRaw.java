package org.market.hedge.bibox.service;

import java.util.List;
import org.knowm.xchange.Exchange;
import org.market.hedge.bibox.BiboxException;
import org.market.hedge.bibox.dto.BiboxCommands;
import org.market.hedge.bibox.dto.BiboxPagedResponses;
import org.market.hedge.bibox.dto.BiboxSingleResponse;
import org.market.hedge.bibox.dto.account.BiboxAsset;
import org.market.hedge.bibox.dto.account.BiboxAssetsResult;
import org.market.hedge.bibox.dto.account.BiboxDeposit;
import org.market.hedge.bibox.dto.account.BiboxFundsCommandBody;
import org.market.hedge.bibox.dto.account.BiboxTransferCommandBody;
import org.market.hedge.bibox.dto.account.BiboxWithdrawal;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.exceptions.ExchangeException;

/** @author odrotleff */
public class BiboxAccountServiceRaw extends BiboxBaseService {

  protected BiboxAccountServiceRaw(Exchange exchange) {
    super(exchange);
  }

  public List<BiboxAsset> getBiboxAccountInfo() {
    try {
      BiboxSingleResponse<BiboxAssetsResult> response =
          bibox.assets(BiboxCommands.ASSETS_CMD.json(), apiKey, signatureCreator);

      throwErrors(response);
      return response.get().getResult().getAssets_list();
    } catch (BiboxException e) {
      throw new ExchangeException(e.getMessage());
    }
  }

  public String requestBiboxDepositAddress(Currency currency) {
    try {
      BiboxSingleResponse<String> response =
          bibox.depositAddress(
              BiboxCommands.depositAddressCommand(currency.getCurrencyCode()).json(),
              apiKey,
              signatureCreator);
      throwErrors(response);
      return response.get().getResult();
    } catch (BiboxException e) {
      throw new ExchangeException(e.getMessage());
    }
  }

  public BiboxPagedResponses.BiboxPage<BiboxWithdrawal> requestBiboxWithdrawals(BiboxFundsCommandBody body) {
    try {
      BiboxPagedResponses<BiboxWithdrawal> response =
          bibox.transferOutList(
              BiboxCommands.withdrawalsCommand(body).json(), apiKey, signatureCreator);
      throwErrors(response);
      BiboxPagedResponses.BiboxPage<BiboxWithdrawal> page = response.get().getResult();
      return page;
    } catch (BiboxException e) {
      throw new ExchangeException(e.getMessage());
    }
  }

  public BiboxPagedResponses.BiboxPage<BiboxDeposit> requestBiboxDeposits(BiboxFundsCommandBody body) {
    try {
      BiboxPagedResponses<BiboxDeposit> response =
          bibox.transferInList(
              BiboxCommands.depositsCommand(body).json(), apiKey, signatureCreator);
      throwErrors(response);
      BiboxPagedResponses.BiboxPage<BiboxDeposit> page = response.get().getResult();
      return page;
    } catch (BiboxException e) {
      throw new ExchangeException(e.getMessage());
    }
  }

  public void requestBiboxWithdraw(BiboxTransferCommandBody body) {
    try {
      BiboxSingleResponse<String> response =
          bibox.transfer(BiboxCommands.transferCommand(body).json(), apiKey, signatureCreator);
      throwErrors(response);
    } catch (BiboxException e) {
      throw new ExchangeException(e.getMessage());
    }
  }
}
