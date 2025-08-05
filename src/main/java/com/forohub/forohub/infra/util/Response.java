package com.forohub.forohub.infra.util;

import com.forohub.forohub.domain.ResponseApi;
import org.springframework.http.ResponseEntity;

public class Response {
    public static ResponseEntity<ResponseApi> success(String message) {
        return ResponseEntity.ok(new ResponseApi(true, message, null));
    }

}
