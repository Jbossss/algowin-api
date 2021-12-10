package org.market.hedge.bibox.coinswap.dto;


import org.market.hedge.bibox.dto.BiboxResponse;

import java.util.List;

/**
 * Result of batched calls with just one call (which happens most of the time)
 *
 * @author odrotleff
 */
public class BiboxCoinSwapSingleResponse<T> extends BiboxResponse<T> {

  public T get() {
    return result;
  }
}
