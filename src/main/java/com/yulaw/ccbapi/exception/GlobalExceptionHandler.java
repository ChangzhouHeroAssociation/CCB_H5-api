package com.yulaw.ccbapi.exception;


import com.yulaw.ccbapi.common.ApiRestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;


/**
 * 处理统一异常的handler
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object handleException(Exception e){
        log.error("Default Exception:" , e);
        return ApiRestResponse.error(CcbExceptionEnum.SYSTEM_ERROR);
    }

    @ExceptionHandler(CcbException.class)
    @ResponseBody
    public Object handleCcbException(CcbException e){
        log.error("CCB Exception:" , e);
        return ApiRestResponse.error(e.getCode(),e.getMessage());
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ApiRestResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        log.error("MethodArgumentNotValidException:" , e);
        return handleBindingResult(e.getBindingResult());
    }

    private ApiRestResponse handleBindingResult(BindingResult result){
        //把异常处理为对外暴露的提示
        List<String> list = new ArrayList<>();
        if(result.hasErrors()){
            List<ObjectError> allErrors = result.getAllErrors();
            for (ObjectError objectError : allErrors) {
                String message = objectError.getDefaultMessage();
                list.add(message);
            }
        }
        if(list.size() == 0){
            return ApiRestResponse.error(CcbExceptionEnum.REQUEST_PARAM_ERROR);
        }
        return ApiRestResponse.error(CcbExceptionEnum.REQUEST_PARAM_ERROR.getCode(),list.toString());

    }
}
