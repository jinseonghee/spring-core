package com.example.aop.aop;


import com.example.aop.dto.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Aspect
@Component
public class DecodeAop {


    @Pointcut("execution(* com.example.aop.controller..*.*(..))")
    private void cut() {

    }

    @Pointcut("@annotation(com.example.aop.annotation.Decode)")
    private void enableDecode() {

    }

    @Before("cut() && enableDecode()")
     public void befor(JoinPoint joinPoint) throws UnsupportedEncodingException {//전은 decode, 후는 encode
        Object[] args = joinPoint.getArgs();

        for (Object arg : args) { //method에 넘어가는 argument를 찾음

            if (arg instanceof User) {
                User user = User.class.cast(arg);
                String base64Email = user.getEmail();
                String email = new String(Base64.getDecoder().decode(base64Email), "UTF-8");
                user.setEmail(email);
            }
        }
    }

    @AfterReturning(value = "cut() && enableDecode()" , returning = "returnObj")
    public void afterReturn(JoinPoint joinPoint, Object returnObj){

        if (returnObj instanceof User) {
            User user = User.class.cast(returnObj);
            String email = user.getEmail();
            String base64Email = Base64.getEncoder().encodeToString(email.getBytes());
            user.setEmail(base64Email);
        }
    }
}