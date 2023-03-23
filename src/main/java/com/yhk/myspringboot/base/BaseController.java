package com.yhk.myspringboot.base;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ModelAttribute;

public class BaseController {

    @ModelAttribute
    public void preHandler(HttpServletRequest request) {
        request.getSession().setAttribute("ctx", request.getContextPath());
    }

    public <T> ResultInfo<T> success() {
        return new ResultInfo<T>();
    }

    public <T> ResultInfo<T> success(String msg) {
        ResultInfo<T> resultInfo = new ResultInfo<>();
        resultInfo.setMsg(msg);
        return resultInfo;
    }

    public <T> ResultInfo<T> success(String msg, T result) {
        ResultInfo<T> resultInfo = new ResultInfo<>();
        resultInfo.setMsg(msg);
        resultInfo.setResult(result);
        return resultInfo;
    }

    public <T> ResultInfo<T> fail(String msg, T result) {
        ResultInfo<T> resultInfo = new ResultInfo<>();
        resultInfo.setMsg(msg);
        resultInfo.setResult(result);
        resultInfo.setCode(500);
        return resultInfo;
    }

    public <T> ResultInfo<T> fail(String msg, Integer code) {
        ResultInfo<T> resultInfo = new ResultInfo<>();
        resultInfo.setMsg(msg);
        resultInfo.setCode(code);
        return resultInfo;
    }
}
