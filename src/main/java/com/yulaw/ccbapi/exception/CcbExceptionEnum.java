package com.yulaw.ccbapi.exception;

/**
 * 异常枚举
 */
public enum CcbExceptionEnum {
    //业务异常
    NEED_PARM(10001,"参数不能为空"),

    //系统异常
    SYSTEM_ERROR(20000,"系统异常");

    /**
     * 异常码
     */
    Integer code;
    /**
     * 异常信息
     */
    String msg;

    CcbExceptionEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
