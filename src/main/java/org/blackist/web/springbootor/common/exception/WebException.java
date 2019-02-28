package org.blackist.web.springbootor.common.exception;

public class WebException extends GlobalException {

    public WebException(String message) {
        super(message);
    }

    public WebException(Exception e) {
        super(e);
    }
}
