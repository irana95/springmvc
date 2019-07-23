package com.spring.Exceptions;

public class ExceptionResp extends RuntimeException {
    private String message;
    private String code;
    private int icode;

    @Override
    public String toString() {
        return "ExceptionResp{" +
                "message='" + message + '\'' +
                ", code='" + code + '\'' +
                ", icode=" + icode +
                '}';
    }

    public int getIcode() {
        return icode;
    }

    public void setIcode(int icode) {
        this.icode = icode;
    }

    public ExceptionResp(int code, String message) {
        this.message = message;
        this.icode = code;
    }

    public ExceptionResp(String code, String message) {
        this.message = message;
        this.code = code;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
