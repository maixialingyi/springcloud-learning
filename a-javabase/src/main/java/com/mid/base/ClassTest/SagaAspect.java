package com.mid.base.ClassTest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class SagaAspect {

    @Around(value = "@annotation(com.mid.base.ClassTest.Saga)")
    public Object sagaFlowProcess(ProceedingJoinPoint joinPoint) throws Throwable {
        return joinPoint.proceed();
    }

}
