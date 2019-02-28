package org.blackist.web.springbootor.common.exception;

import org.blackist.web.springbootor.common.response.ErrorCode;

import java.io.Serializable;

public class GlobalException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 1L;

    protected int code;

    public GlobalException(String message) {
        super(message);
        code = ErrorCode.ERROR;
    }

    public GlobalException(Exception e) {
        super(e);
        code = ErrorCode.ERROR;
    }

    @Override
    public String getMessage() {
        return super.getMessage().equals("") ? "Server Error" : super.getMessage();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
