package org.market.hedge.okex.dto.trade.resp;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OkexCancelAllResp {

    public OkexCancelAllResp (
            @JsonProperty("error_code") String error_code,
            @JsonProperty("error_message") String error_message,
            @JsonProperty("result") String result) {
        setError_code(error_code);
        setError_message(error_message);
        setResult(result);

    }

    /**
     * 错误码，请求成功时为0，请求失败时会显示相应错误码
     */
    String error_code;

    /**
     * 错误信息，请求成功时为空，请求失败时会显示错误信息
     */
    String error_message;

    /**
     * 接口调用返回结果，true/false
     */
    String result;

    public String getError_code() {
        return error_code;
    }

    public String getError_message() {
        return error_message;
    }

    public String getResult() {
        return result;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public void setError_message(String error_message) {
        this.error_message = error_message;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
