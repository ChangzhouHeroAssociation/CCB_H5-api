package com.yulaw.ccbapi.exception;

/**
 * 异常枚举
 */
public enum CcbExceptionEnum {
    //业务异常
    NEED_PARM(101,"参数不能为空"),
    DATA_NOT_FOUND(102,"未找到该数据"),
    RESULT_NOT_ONLY(103,"查询结果不唯一"),
    QUESTION_TYPE_ERROR(104,"问题类型错误"),
    INSERT_LOST(105,"部分数据未成功插入"),
    ADD_QUESTION_FAILED(106,"提交问卷失败"),
    REQUEST_PARAM_NOT_FOUND(107,"未找到该字段"),
    REQUEST_PARAM_ERROR(108,"请求参数错误"),
    MKDIR_FAILED(109,"创建文件夹失败"),
    UPLOAD_FAILED(110,"图片上传失败"),
    GET_TEACHER_FAILED(111,"获取讲师信息失败"),
    GET_VIDEO_FAILED(112,"获取视频信息失败"),
    GET_BANNER_FAILED(113,"获取banner信息失败"),


    //系统异常
    SYSTEM_ERROR(500,"系统异常");

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
