package com.example.aop.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect //aop로 동작하기 위한 @annotation
@Component //spring에서 관리를 해달라는 의미의 @annotation
public class ParameterAop {

    @Pointcut("execution(* com.example.aop.controller..*.*(..))") //내가 어느 부분에 적용 시킬건지 적용(괄호 안엔 Rule 설정, controller 패키지 하위에 있는 모든 메서 적용 )
    private void cut() {

    }

    @Before("cut()") //위에 cut 메서드가 실행되는 시점의 전에 이 메서드를 실행 시켜라
    public void before(JoinPoint joinPoint) {//메서드가 실행되기 전에 넘어가는 argument 값이 뭔지 확인
                                             // JoinPoint는 들어가는 지점에 대한 정보들을 갖고 있는 객체

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature(); //(MethodSignature)로 형변환
        Method method = methodSignature.getMethod();
        System.out.println(method.getName()); //method 이름 가져옴

        Object[] args = joinPoint.getArgs(); //메서드에 들어가고 있는 argument들(매개변수들)의 배열

        for(Object obj : args) {
            System.out.println("type : " + obj.getClass().getSimpleName());
            System.out.println("value : " + obj);
        }
    }

    @AfterReturning(value = "cut()", returning = "returnObj") //returning이라는 속성은 내가 받고싶은 객체의 이름을 넣어줌
    public void afterReturn(JoinPoint joinPoint, Object returnObj) { //@AfterReturning은 Object를 받을 수 있음.

        System.out.println("return obj");
        System.out.println(returnObj);
    }
}
