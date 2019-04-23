package com.bkhech.boot.configure.mvc.log;

import com.bkhech.boot.configure.common.code.StatusCode;
import com.bkhech.boot.configure.common.dto.ResultResponse;
import com.bkhech.boot.configure.common.exception.SystemException;
import com.bkhech.boot.configure.web.log.HttpLogInfo;
import com.bkhech.boot.configure.web.log.HttpLogRequest;
import com.bkhech.boot.configure.web.log.ThrowHttpStatusHandler;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

import static com.bkhech.boot.configure.common.constants.ConfigureConstants.GLOBAL_ERROR_KEY;


/**
 * <p>统一日志记录</p>
 *
 * Created by guowm on 2016/12/17.
 */
@Aspect
@Component
public class MvcHttpLogHandler {

    /**
     * 定义日志切入点
     */
    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping) " +
            "|| @annotation(org.springframework.web.bind.annotation.PostMapping) " +
            "|| @annotation(org.springframework.web.bind.annotation.GetMapping)" +
            "|| @annotation(org.springframework.web.bind.annotation.PutMapping)" +
            "|| @annotation(org.springframework.web.bind.annotation.PatchMapping)" +
            "|| @annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    public void logAspect() {}

    @Around("logAspect()")
    public Object around(ProceedingJoinPoint point) {
        Object[] args = point.getArgs();

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        HttpLogRequest logRequest = new HttpLogRequest(request.getRequestURI(), request.getMethod());
        HttpLogInfo logInfo = new HttpLogInfo(point, logRequest);
        logInfo.beforeLog();

        boolean isThrow = ThrowHttpStatusHandler.validateThrowHttpStatus(logInfo);
        try {
            Object result = point.proceed(args);
            logInfo.afterLog(result);
            return result;
        } catch (SystemException e) {
            logInfo.throwLog(e.getCode() + " : " + e.getMessage(), e);
            if (isThrow) {
                request.setAttribute(GLOBAL_ERROR_KEY, e.getCode());
                throw new SystemException(e.getCode(), e.getMessage());
            } else {
                return ResultResponse.custom(e.getCode(), e.getMessage());
            }
        } catch (ResourceAccessException e) {
            logInfo.throwLog(e.getMessage(), e);
            return ResultResponse.custom(StatusCode.HTTP_ERROR);
        } catch (Throwable e) {
            logInfo.throwLog(e.getMessage(), e);
            if (isThrow) {
                throw new SystemException(StatusCode.FAILED);
            }
            return ResultResponse.custom(StatusCode.UNKNOWN);
        }
    }

}
