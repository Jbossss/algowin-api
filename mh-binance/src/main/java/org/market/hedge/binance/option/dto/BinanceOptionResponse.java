package org.market.hedge.binance.option.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import si.mazi.rescu.ExceptionalReturnContentException;

/**
 * V represents result class of the queried endpoint
 * {
 *   "code": 0,
 *   "msg": "success",
 *   "data": [
 *   ]
 * }
 * */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class BinanceOptionResponse<V> {

  @JsonProperty("code")
  private Integer code;

  @JsonProperty("msg")
  private String msg;

  @JsonProperty("data")
  private V data;


  public BinanceOptionResponse() {}

  public BinanceOptionResponse(
          Integer code,
          String msg,
          V data) {

    /** This will cause parsing the response body as an DeribitException */
    if (code != 0) {
      throw new ExceptionalReturnContentException(msg);
    }

    this.code = code;
    this.msg = msg;
    this.data = data;
  }
}
