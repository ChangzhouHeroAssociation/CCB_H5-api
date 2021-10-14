package com.yulaw.ccbapi.exception;

/**
 * 异常枚举
 */
public enum CcbExceptionEnum {
    //业务异常
    NEED_PARM(10001,"参数不能为空"),
    DATA_NOT_FOUND(10002,"未找到该数据"),
    RESULT_NOT_ONLY(10003,"查询结果不唯一"),
    ADD_QUESTION_FAILED(10006,"提交问卷失败"),
    REQUEST_PARAM_NOT_FOUND(10007,"未找到该字段"),
    REQUEST_PARAM_ERROR(10008,"请求参数错误"),
    MKDIR_FAILED(10009,"创建文件夹失败"),
    UPLOAD_FAILED(100010,"图片上传失败"),
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
