package com.backend.dto;

public class ResponseObj {
    private String data;

    public ResponseObj() {

    }

    public ResponseObj(String data) {
        this.data = data;
    }

    public String getData() {
        return this.data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
