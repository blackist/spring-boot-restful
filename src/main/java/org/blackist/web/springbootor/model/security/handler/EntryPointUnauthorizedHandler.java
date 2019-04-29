package org.blackist.web.springbootor.model.security.handler;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("unauthorizedHandler")
public class EntryPointUnauthorizedHandler implements AuthenticationEntryPoint {


    /**
     * 未登录或无权限操作时触发
     * 返回  {"code":401,"message":"未携带 token 或者 token 无效","data":""}
     *
     * @param httpServletRequest  request
     * @param httpServletResponse response
     * @param e                   exception
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.getWriter().print("{\"code\":\"401\",\"data\":\"\",\"message\":\"未携带 token 或 token 无效\"}");
        httpServletResponse.getWriter().flush();
    }
}
