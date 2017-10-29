package com.oa.core.exception;

/**
 * 自定义异常
 * Created by [张渊]
 * 2017/10/7 15:13
 */
public class CustomException extends Exception {

    private String msg;

    public CustomException(String message) {
        super(message);
        this.msg = message;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
