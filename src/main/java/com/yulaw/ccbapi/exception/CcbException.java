package com.yulaw.ccbapi.exception;

public class CcbException extends RuntimeException{

    private final Integer code;
    private final String message;

    public CcbException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public CcbException(CcbExceptionEnum exceptionEnum) {
        this.code = exceptionEnum.getCode();
        this.message = exceptionEnum.getMsg();
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
