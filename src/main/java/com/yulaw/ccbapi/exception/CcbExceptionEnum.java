package com.yulaw.ccbapi.exception;

/**
 * 异常枚举
 */
public enum CcbExceptionEnum {
    //业务异常
    NEED_PARM(10001,"参数不能为空"),
    DATA_NOT_FOUND(10002,"未找到该数据"),
    QUESTION_NOT_NULL(10003,"问题编号不能为空"),
    RESULT_NOT_NULL(10004,"回答结果不能为空"),
    CHANNEL_NOT_NULL(10005,"对应频道不能为空"),
    ADD_QUESTION_FAILED(10006,"提交问卷失败"),
    REQUEST_PARAM_ERROR(10007,"请求参数错误"),
    MKDIR_FAILED(10008,"创建文件夹失败"),
    UPLOAD_FAILED(10009,"图片上传失败"),
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
