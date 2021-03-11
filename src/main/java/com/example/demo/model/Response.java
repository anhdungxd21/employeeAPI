package com.example.demo.model;

import lombok.Data;

@Data
public class Response {
    private int status;
    private String message;
    private Object data;

    public Response(){}
    public Response(int status, String message, Object data){
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
