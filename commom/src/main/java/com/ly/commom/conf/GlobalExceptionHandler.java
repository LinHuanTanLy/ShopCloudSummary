package com.ly.commom.conf;

import com.ly.commom.bean.Resp;
import com.ly.commom.exception.RException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NestedRuntimeException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    /**
     * 普通异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public Resp<String> defaultExceptionHandler(Exception e) {
        System.out.println(String.format("开始异常处理: %s", e.getMessage()));
        String errMsg = e.getMessage();
        if (StringUtils.isEmpty(errMsg)) {
            errMsg = e.getLocalizedMessage();
        }
        return Resp.fail(errMsg);
    }

    /**
     * 参数校验异常，将校验失败的所有异常组合成一条错误信息
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Resp<String> myExceptionHandler(MethodArgumentNotValidException e) {
        System.out.println("出现自定义异常了 : " + e.getMessage());
        return Resp.fail(Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage());
    }

    /**
     * 自定义异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = RException.class)
    public Resp<String> myExceptionHandler(RException e) {
        System.out.println("出现自定义异常了 : " + e.getMessage());
        return Resp.fail(e.getMsg());
    }

    /**
     * 参数绑定异常
     *
     * @param bindingResult
     * @return
     */
    @ExceptionHandler(value = {BindException.class})
    public Resp<String> exceptionMessage(BindException bindingResult) {
        System.out.println("参数校验异常 : ");
        return Resp.fail(bindingResult.getMessage());
    }
}
