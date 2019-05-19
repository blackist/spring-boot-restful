package org.blackist.web.springbootor.common.aspect;

import com.google.gson.Gson;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.blackist.web.springbootor.common.util.GsonUtil;
import org.blackist.web.springbootor.model.entity.system.SysLog;
import org.blackist.web.springbootor.service.system.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Slf4j
@Aspect
@Component
public class WebLogAspect {

    @Autowired
    private SysLogService sysLogService;

    @Pointcut("execution(public * org.blackist.web.springbootor.web..*.*(..))")
    // @Pointcut("@annotation(org.blackist.web.springbootor.common.aspect.WebLog)")
    public void webLog() {

    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 记录下请求内容
        log.info("<============ HTTP START ============");
        log.info("URL:           " + request.getRequestURL().toString());
        log.info("HTTP_METHOD :  " + request.getMethod());
        log.info("IP :           " + request.getRemoteAddr());
        log.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.info("ARGS :         " + Arrays.toString(joinPoint.getArgs()));
    }

    @Around("webLog()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        Object result = point.proceed();
        long time = System.currentTimeMillis() - beginTime;

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        try {
            saveLog(point, request, time);
            log.info("REQUEST TIME: " + time);
        } catch (Exception e) {

        }
        return result;
    }

    /**
     * 保存日志
     *
     * @param joinPoint
     * @param time
     */
    private void saveLog(ProceedingJoinPoint joinPoint, HttpServletRequest request, long time) {
        SysLog sysLog = new SysLog();

        sysLog.setExecuteTime(time);
        sysLog.setCreateTime(new Date());

        // Http
        sysLog.setRequestURL(request.getRequestURL().toString());
        sysLog.setRequestURI(request.getRequestURI());
        sysLog.setQueryString(request.getQueryString());
        sysLog.setRemoteAddr(request.getRemoteAddr());
        sysLog.setRemoteHost(request.getRemoteHost());
        sysLog.setRemoteAddr(request.getRemoteAddr());
        sysLog.setRemoteHost(request.getRemoteHost());
        sysLog.setRemotePort(request.getRemotePort());
        sysLog.setLocalName(request.getLocalName());
        // sysLog.setHeaders(gson.toJson(getHeadersInfo(request)));

        // Method Params
        sysLog.setClassMethod(joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        Object[] args = joinPoint.getArgs();
        try {
            List<String> list = new ArrayList<>();
            for (Object o : args) {
                list.add(GsonUtil.getInstance().toJson(o));
            }
            sysLog.setParams(list.toString());
        } catch (Exception e) {
        }
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        WebLog webLog = method.getAnnotation(WebLog.class);
        if (webLog != null) {
            //注解上的描述
            sysLog.setRemark(webLog.value());
        } else {
            // swagger上的注释
            ApiOperation apiOperation = method.getAnnotation(ApiOperation.class);
            if (apiOperation != null) {
                sysLog.setRemark(apiOperation.value());
            }
        }

        sysLogService.save(sysLog);
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        log.info("RESPONSE     : " + GsonUtil.getInstance().toJson(ret));
        log.info("============= HTTP END ==============>");
    }

    private Map<String, String> getHeadersInfo(HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        return map;
    }
}
