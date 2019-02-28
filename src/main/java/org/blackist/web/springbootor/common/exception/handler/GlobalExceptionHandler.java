package org.blackist.web.springbootor.common.exception.handler;

import org.blackist.web.springbootor.common.constants.HttpConstants;
import org.blackist.web.springbootor.common.exception.ApiException;
import org.blackist.web.springbootor.common.exception.WebException;
import org.blackist.web.springbootor.common.response.ErrorCode;
import org.blackist.web.springbootor.common.response.Response;
import org.blackist.web.springbootor.common.util.CommonUtil;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final String ERROR = "error";
    private static final String ERROR_E = "e";
    private static final String ERROR_URL = "url";
    private static final String ERROR_CODE = "code";
    private static final String ERROR_MESSAGE = "message";
    private static final String ERROR_EXCEPTION = "exception";

    @ExceptionHandler(Exception.class)
    public Object handle(HttpServletRequest req, Exception e) {
        if (e instanceof ApiException) {
            return new Response(
                    ((ApiException) e).getCode(),
                    e.getMessage());
        } else if (e instanceof WebException) {
            Map<String, Object> error = new HashMap<>();
            error.put(ERROR_URL, req.getRequestURL());
            error.put(ERROR_CODE, ErrorCode.HTTP_500);
            error.put(ERROR_MESSAGE, CommonUtil.isEmpty(e.getMessage()) ? "Unknown Error" : e.getMessage());
            error.put(ERROR_EXCEPTION, e);

            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject(ERROR_E, error);
            modelAndView.setViewName(ERROR);
            return modelAndView;
        } else {
            String contentTypeHeader = req.getHeader(HttpConstants.CONTENT_TYPE);
            String acceptHeader = req.getHeader(HttpConstants.ACCEPT);
            String xRequestedWith = req.getHeader(HttpConstants.REQUEST_WITH);
            if ((contentTypeHeader != null && contentTypeHeader.contains(HttpConstants.CONTENT_TYPE_JSON))
                    || (acceptHeader != null && acceptHeader.contains(HttpConstants.CONTENT_TYPE_JSON))
                    || HttpConstants.REQUEST_WITH_XML.equalsIgnoreCase(xRequestedWith)) {
                return new Response(
                        ErrorCode.HTTP_500,
                        CommonUtil.isEmpty(e.getMessage()) ? "Unknown Error" : e.getMessage());
            } else {
                Map<String, Object> error = new HashMap<>();
                error.put(ERROR_URL, req.getRequestURL());
                error.put(ERROR_CODE, ErrorCode.HTTP_500);
                error.put(ERROR_MESSAGE, CommonUtil.isEmpty(e.getMessage()) ? "Unknown Error" : e.getMessage());
                error.put(ERROR_EXCEPTION, e);

                ModelAndView modelAndView = new ModelAndView();
                modelAndView.addObject(ERROR_E, error);
                modelAndView.setViewName(ERROR);
                return modelAndView;
            }
        }

    }
}
