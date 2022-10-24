

package com.project.common.aspect;

import com.project.common.utils.HttpContextUtils;
import com.project.common.utils.IpUtils;
import com.project.common.utils.JsonUtils;
import com.project.modules.security.user.SecurityUser;
import com.project.modules.security.user.UserDetail;
import com.project.common.annotation.LogOperation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 *
 *
 *
 */
@Aspect
@Component
public class LogOperationAspect {

    @Pointcut("@annotation(com.project.common.annotation.LogOperation)")
    public void logPointCut() {

    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        try {
            //manner of execution
            Object result = point.proceed();


            long time = System.currentTimeMillis() - beginTime;

//            saveLog(point, time, OperationStatusEnum.SUCCESS.value());
//
            return result;
        }catch(Exception e) {

            long time = System.currentTimeMillis() - beginTime;

            throw e;
        }
    }

    private void saveLog(ProceedingJoinPoint joinPoint, long time, Integer status) throws Exception {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = joinPoint.getTarget().getClass().getDeclaredMethod(signature.getName(), signature.getParameterTypes());
        LogOperation annotation = method.getAnnotation(LogOperation.class);


        //Information about the logged-in user
        UserDetail user = SecurityUser.getUser();
        if(user != null){
        }


        //request parameter
        Object[] args = joinPoint.getArgs();
        try{
            String params = JsonUtils.toJsonString(args[0]);
//            log.setRequestParams(params);
        }catch (Exception e){

        }

    }
}