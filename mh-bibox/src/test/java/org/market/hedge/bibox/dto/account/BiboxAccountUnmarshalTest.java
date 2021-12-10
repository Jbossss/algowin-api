package org.market.hedge.bibox.dto.account;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.type.TypeReference;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import org.junit.Test;
import org.market.hedge.bibox.BiboxTestUtils;
import org.market.hedge.bibox.dto.BiboxSingleResponse;

/**
 * Test Account JSON parsing
 *
 * @author odrotleff
 */
public class BiboxAccountUnmarshalTest {

  @Test
  public void testTickerUnmarshal() throws IOException {

    BiboxSingleResponse<List<BiboxCoin>> response =
        BiboxTestUtils.getResponse(
            new TypeReference<BiboxSingleResponse<List<BiboxCoin>>>() {},
                "/org/market/hedge/huobi/bibox/dto/account/example-coin-list.json");
    assertThat(response.get().getCmd()).isEqualTo("transfer/coinList");

    List<BiboxCoin> coinList = response.get().getResult();
    assertThat(coinList).hasSize(3);

    BiboxCoin etc = coinList.get(2);
    assertThat(etc.getSymbol()).isEqualTo("ETC");
    assertThat(etc.getId()).isEqualTo(45);
    assertThat(etc.getTotalBalance()).isEqualTo(new BigDecimal("0.40652300"));
    assertThat(etc.getBalance()).isEqualTo(new BigDecimal("0.40652300"));
    assertThat(etc.getFreeze()).isEqualTo(new BigDecimal("0.00000000"));
  }
}
