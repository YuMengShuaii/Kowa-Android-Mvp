package com.nat.android.javashoplib.aop;

import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by LDD on 17/4/13.
 */
@Aspect
public class LoginAspect {


    @Pointcut("execution(@com.nat.android.javashoplib.aop.isLogin * *(..))")//根据SingleClick注解找到方法切入点
    public void methodAnnotated() {
        Log.e("AOP","ANOTATED");
    }

    @Around("methodAnnotated()")//在连接点进行方法替换
    public void aroundJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {
        Log.e("AOP","JoinPoint");
        joinPoint.proceed();
        return;
    }

}
