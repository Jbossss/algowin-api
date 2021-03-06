package org.market.hedge.deribit.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class DeribitGreeks {

  /** The vega value for the option */
  @JsonProperty("vega")
  private BigDecimal vega;

  /** The theta value for the option */
  @JsonProperty("theta")
  private BigDecimal theta;

  /** The rho value for the option */
  @JsonProperty("rho")
  private BigDecimal rho;

  /** The gamma value for the option */
  @JsonProperty("gamma")
  private BigDecimal gamma;

  /** The delta value for the option */
  @JsonProperty("delta")
  private BigDecimal delta;
}
