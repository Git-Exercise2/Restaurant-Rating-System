

package io.renren.common.aspect;

import io.renren.common.annotation.LogOperation;
import io.renren.common.utils.HttpContextUtils;
import io.renren.common.utils.IpUtils;
import io.renren.common.utils.JsonUtils;
import io.renren.modules.log.entity.SysLogOperationEntity;
import io.renren.modules.log.enums.OperationStatusEnum;
import io.renren.modules.log.service.SysLogOperationService;
import io.renren.modules.security.user.SecurityUser;
import io.renren.modules.security.user.UserDetail;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Operation log, facet processing class
 *
 * @author Mark sunlightcs@gmail.com
 */
@Aspect
@Component
@AllArgsConstructor
public class LogOperationAspect {
    private final SysLogOperationService sysLogOperationService;

    @Pointcut("@annotation(io.renren.common.annotation.LogOperation)")
    public void logPointCut() {

    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        try {
            //How to execute
            Object result = point.proceed();

            //Execution duration(毫秒)
            long time = System.currentTimeMillis() - beginTime;
            //Save logs
            saveLog(point, time, OperationStatusEnum.SUCCESS.value());

            return result;
        } catch (Exception e) {
            //Execution duration(毫秒)
            long time = System.currentTimeMillis() - beginTime;
            //Save logs
            saveLog(point, time, OperationStatusEnum.FAIL.value());

            throw e;
        }
    }

    private void saveLog(ProceedingJoinPoint joinPoint, long time, Integer status) throws Exception {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = joinPoint.getTarget().getClass().getDeclaredMethod(signature.getName(), signature.getParameterTypes());
        LogOperation annotation = method.getAnnotation(LogOperation.class);

        SysLogOperationEntity log = new SysLogOperationEntity();
        if (annotation != null) {
            //Description on the notation
            log.setOperation(annotation.value());
        }

        //Login user information
        UserDetail user = SecurityUser.getUser();
        if (user != null) {
            log.setCreatorName(user.getUsername());
        }

        log.setStatus(status);
        log.setRequestTime((int) time);

        //Request relevant information
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        log.setIp(IpUtils.getIpAddr(request));
        log.setUserAgent(request.getHeader(HttpHeaders.USER_AGENT));
        log.setRequestUri(request.getRequestURI());
        log.setRequestMethod(request.getMethod());

        //Request parameters
        Object[] args = joinPoint.getArgs();
        try {
            String params = JsonUtils.toJsonString(args[0]);
            log.setRequestParams(params);
        } catch (Exception e) {

        }

        //Save toDB
        sysLogOperationService.save(log);
    }
}
