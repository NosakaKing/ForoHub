package com.forohub.forohub.domain;

import lombok.Data;

@Data
public class ResponseApi {
    private boolean status;
    private String message;
    private Object data;

    public ResponseApi(boolean status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
