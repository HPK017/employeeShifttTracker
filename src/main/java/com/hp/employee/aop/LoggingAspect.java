package com.hp.employee.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Aspect
@Configuration
@Slf4j
public class LoggingAspect {

    @Pointcut("execution(* com.hp.employee.serviceImpl..*(..))")
    public void serviceLayer(){
    }

    @Before("serviceLayer()")
    public void logMethodEntry(JoinPoint joinPoint) {
        log.info("ENTER METHOD: {} ", joinPoint.getSignature().toShortString());

        log.info("ARGUMENTS: {} ", Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(
            pointcut = "serviceLayer()",
            returning = "result"
    )
    public void logMethodExit(JoinPoint joinPoint, Object result) {
        log.info("EXIT METHOD: {} ", joinPoint.getSignature().toShortString());

        log.info("RETURN VALUE: {}", result);
    }

    @AfterThrowing(
            pointcut = "serviceLayer()",
            throwing = "exception"
    )
    public void logMethodExecution(JoinPoint joinPoint, Exception exception) {
        log.error("EXCEPTION IN METHOD: {}", joinPoint.getSignature().toShortString());
        log.error("EXCEPTION MESSAGE {}", exception.getMessage());
    }

    @Around("serviceLayer()")
    public Object longExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable{
        long start = System.currentTimeMillis();

        Object result = joinPoint.proceed();

        long executionTime = System.currentTimeMillis() - start;

        log.info("METHOD {} EXECUTED IN {} ms", joinPoint.getSignature().toShortString(),
                executionTime
        );

        return result;

    }


}
