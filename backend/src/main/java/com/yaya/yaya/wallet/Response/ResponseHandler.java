package com.yaya.yaya.wallet.Response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {
    public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object responseObject){
        Map<String, Object>    map =new HashMap<String, Object>();
        map.put("Message" , message);
        map.put("Status", status.value());
        map.put("Data", responseObject);
        return new ResponseEntity<Object>(map, status);
    }
}
