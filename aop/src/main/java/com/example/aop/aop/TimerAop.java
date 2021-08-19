package com.example.aop.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class TimerAop {

    @Pointcut("execution(* com.example.aop.controller..*.*(..))") //controller 하위에 있는 method들만 제약
    private void cut() {

    }

    @Pointcut("@annotation(com.example.aop.annotation.Timer)") //Timer 하위에 있는 @annotation이 설정된 method들만 제약
    private void enableTimer() {

    }

    @Around("cut() && enableTimer()") //두가지 조건을 같이 쓴다는 의미
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable { //Before 메서드와 after메서드는 time을 공유 할 수가 없음

        //실행 전
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Object result = proceedingJoinPoint.proceed(); //proceed 메서드 호출 한 후 Object로 return

        //실행 후
        stopWatch.stop();

        System.out.println("total time : " + stopWatch.getTotalTimeSeconds());
    }
}
