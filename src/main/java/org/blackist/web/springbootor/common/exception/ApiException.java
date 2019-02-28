package org.blackist.web.springbootor.common.exception;

public class ApiException extends GlobalException {

    public ApiException(String message) {
        super(message);
    }

    public ApiException(Exception e) {
        super(e);
    }
}
