package org.blackist.web.springbootor.common.aspect;

import com.mongodb.BasicDBObject;
import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.blackist.web.springbootor.common.util.CommonUtil;
import org.blackist.web.springbootor.common.util.GsonUtil;
import org.blackist.web.springbootor.model.entity.system.WebLog;
import org.blackist.web.springbootor.service.system.WebLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.*;

@Aspect
@Component
public class WebLogAspect {

    private Logger log = LoggerFactory.getLogger(WebLogAspect.class);

    @Autowired
    private WebLogService webLogService;

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
        log.debug("<============ HTTP START ============");
        log.debug("URL:           {}", request.getRequestURL().toString());
        log.debug("HTTP_METHOD :  {}", request.getMethod());
        log.debug("IP :           {}", request.getRemoteAddr());
        log.debug("CLASS_METHOD : {}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.debug("ARGS :         {}", Arrays.toString(joinPoint.getArgs()));

        BasicDBObject object = getBasicDBObject(request, joinPoint);
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
            log.debug("REQUEST TIME: {}", time);
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
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        org.blackist.web.springbootor.common.aspect.WebLog webLog = method.getAnnotation(org.blackist.web.springbootor.common.aspect.WebLog.class);
        if (webLog == null) {
            return;
        }

        WebLog sysLog = new WebLog();
        sysLog.setExecuteTime(time);
        if (CommonUtil.isNotEmpty(webLog.value())) {
            //注解上的描述
            sysLog.setRemark(webLog.value());
        } else {
            // swagger上的注释
            ApiOperation apiOperation = method.getAnnotation(ApiOperation.class);
            if (apiOperation != null) {
                sysLog.setRemark(apiOperation.value());
            }
        }
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
        sysLog.setHeaders(GsonUtil.getInstance().toJson(getHeadersInfo(request)));
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

        webLogService.save(sysLog);
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        log.debug("RESPONSE     : {}", GsonUtil.getInstance().toJson(ret));
        log.debug("============= HTTP END ==============>");
    }

    private BasicDBObject getBasicDBObject(HttpServletRequest request, JoinPoint joinPoint) {
        // 基本信息
        BasicDBObject r = new BasicDBObject();
        r.append("requestURL", request.getRequestURL().toString());
        r.append("requestURI", request.getRequestURI());
        r.append("queryString", request.getQueryString());
        r.append("remoteAddr", request.getRemoteAddr());
        r.append("remoteHost", request.getRemoteHost());
        r.append("remotePort", request.getRemotePort());
        r.append("localAddr", request.getLocalAddr());
        r.append("localName", request.getLocalName());
        r.append("method", request.getMethod());
        r.append("headers", getHeadersInfo(request));
        r.append("parameters", request.getParameterMap());
        r.append("classMethod", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        r.append("args", Arrays.toString(joinPoint.getArgs()));
        return r;
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
