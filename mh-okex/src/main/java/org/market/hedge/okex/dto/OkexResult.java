package org.market.hedge.okex.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class OkexResult<V> {

  private final String code;
  private final String msg;
  private final V data;

  @JsonCreator
  public OkexResult(
      @JsonProperty("code") String code,
      @JsonProperty("msg") String msg,
      V data) {
    this.code = code;
    this.msg = msg;
    this.data = data;
  }

  public Boolean isSuccess(){
    return Integer.parseInt(code) == 0;
  }

  public String getError() {
    return (msg.length() == 0) ? code : msg;
  }

  public V getData() {
    return data;
  }

}
