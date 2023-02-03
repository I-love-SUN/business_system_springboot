package com.ljc.springboot.exception;

public class ServiceException extends RuntimeException{
    private String code;

    public String getCode() {
        return code;
    }

    public ServiceException(String code, String msg) {
        super(msg);
        this.code = code;
    }
}
