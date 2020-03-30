package com.ly.commom.conf;

import com.ly.commom.bean.Resp;
import com.ly.commom.exception.RException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public Resp<String> defaultExceptionHandler(Exception e) {
        System.out.println(String.format("开始异常处理: %s", e.getMessage()));
        String errMsg = e.getMessage();
        if (StringUtils.isEmpty(errMsg)) {
            errMsg = e.getLocalizedMessage();
        }
        return Resp.fail(errMsg);
    }

    @ExceptionHandler(value = RException.class)
    public Resp<String> myExceptionHandler(RException e) {
        System.out.println("出现自定义异常了 : " + e.getMessage());
        return Resp.fail(e.getMsg());
    }
}
