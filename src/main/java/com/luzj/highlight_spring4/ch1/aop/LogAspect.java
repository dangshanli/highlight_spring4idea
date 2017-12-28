package com.luzj.highlight_spring4.ch1.aop;

import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by Administrator on 2017/12/28.
 */
@Component
@Aspect
public class LogAspect {
    //定义建言，拦截方法
    @Before("execution(com.luzj.highlight_spring4.ch1.aop.DemoMethodService.*(..))")
    public void before(JoinPoint joinPoint){
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        Method method = signature.getMethod();
        System.out.println("方法规则拦截："+method.getName());
    }
    //定义切点
    @Pointcut("@annotation(com.luzj.highlight_spring4.ch1.aop.Action)")
    public void AnnotationPointCut(){}

    //定义建言,拦截注解
    @After("AnnotationPointCut()")
    public void after(JoinPoint joinPoint){
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        Method method = signature.getMethod();
        Action action = method.getAnnotation(Action.class);
        System.out.println("注解式拦截："+action.name());
    }

    /*@Before("AnnotationPointCut()")
    public void before(Joinpoint joinPoint){
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
    }*/
}
