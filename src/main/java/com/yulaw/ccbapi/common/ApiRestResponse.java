package com.yulaw.ccbapi.common;

import com.yulaw.ccbapi.exception.CcbExceptionEnum;

/**
 * 通用返回对象
 */
public class ApiRestResponse<T> extends BaseResponse{

    private Integer status;

    private String msg;

    private T data;

    private static final int OK_CODE = 200;

    private static final String OK_MSG = "SUCCESS";

    public ApiRestResponse(Integer status, String msg, T data) {
        super(status,msg);
        this.data = data;
    }

    public ApiRestResponse(Integer status, String msg) {
        super(status,msg);
    }

    public ApiRestResponse() {
        this(OK_CODE,OK_MSG);
    }

    public static <T> ApiRestResponse<T> success(){
        return new ApiRestResponse<>();
    }

    public static <T> ApiRestResponse<T> success(T result){
        ApiRestResponse<T> response =  new ApiRestResponse<>();
        response.setData(result);
        return response;
    }

    public static BaseResponse error(Integer code,String msg){
        BaseResponse baseResponse = new BaseResponse(code, msg);
        return baseResponse;
    }

    public static BaseResponse error(CcbExceptionEnum ex){
        BaseResponse baseResponse = new BaseResponse(ex.getCode(), ex.getMsg());
        return baseResponse;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static int getOkCode() {
        return OK_CODE;
    }

    public static String getOkMsg() {
        return OK_MSG;
    }

    @Override
    public String toString() {
        return "ApiRestResponse{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
