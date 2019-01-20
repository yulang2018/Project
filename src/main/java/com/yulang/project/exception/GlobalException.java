package com.yulang.project.exception;

import com.yulang.project.response.BaseResponse;
import com.yulang.project.response.CodeMessage;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@ResponseBody
public class GlobalException {

    @ExceptionHandler
    public BaseResponse exception(HttpServletRequest httpServletRequest, Exception e){
        e.printStackTrace();
        return BaseResponse.erro(CodeMessage.ERROR);
    }

}
