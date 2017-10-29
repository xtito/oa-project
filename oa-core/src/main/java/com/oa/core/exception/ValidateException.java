package com.oa.core.exception;

/**
 * Created by [张渊]
 * 2017/8/10 11:29
 */
public class ValidateException extends Exception {

    public enum ValidateErrorType{
        empty,maxLength,number,integral,email,max,min,minLength,reg
    }
    private String msg;
    private String col;
    private ValidateErrorType errorType;

    public ValidateException(String msg,String col,ValidateErrorType errorType){
        super(msg);
    }

    public ValidateException(String msg) {
        super(msg);
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCol() {
        return col;
    }

    public void setCol(String col) {
        this.col = col;
    }

    public ValidateErrorType getErrorType() {
        return errorType;
    }

    public void setErrorType(ValidateErrorType errorType) {
        this.errorType = errorType;
    }

}
