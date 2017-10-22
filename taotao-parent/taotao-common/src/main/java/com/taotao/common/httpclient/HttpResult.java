package com.taotao.common.httpclient;

public class HttpResult {
    private Integer code;
    private String date;

    public HttpResult() {
    }

    public HttpResult(Integer code, String date) {
        this.code = code;
        this.date = date;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
