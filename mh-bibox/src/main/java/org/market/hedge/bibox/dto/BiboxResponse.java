package org.market.hedge.bibox.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * General Bibox response
 *
 * @param <T> Result type
 * @author odrotleff
 */
public class BiboxResponse<T> {

  @JsonProperty("result")
  protected T result;
  protected String cmd;
  protected String order_id;
  protected String  msg;
  protected Integer state;
  protected BiboxError error = null;

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public void setOrder_id(String order_id) {
    this.order_id = order_id;
  }

  public String getOrder_id() {
    return order_id;
  }

  public Integer getState() {
    return state;
  }

  public void setState(Integer state) {
    this.state = state;
  }

  public T getResult() {
    return result;
  }

  public void setResult(T result) {
    this.result = result;
  }

  public String getCmd() {
    return cmd;
  }

  public void setCmd(String cmd) {
    this.cmd = cmd;
  }

  public BiboxError getError() {
    return error;
  }

  public void setError(BiboxError error) {
    this.error = error;
  }
}
