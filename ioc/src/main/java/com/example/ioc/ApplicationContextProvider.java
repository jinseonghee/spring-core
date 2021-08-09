package com.example.ioc;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextProvider implements ApplicationContextAware {

    private static ApplicationContext context; //밑에 오버라이딩한 메서드에 ApplicationContext가 주입을 받고 있어서 선언해줌

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext; //static으로 선언해 주었기 때문에 this 쓸 필요 없음.
    }

    public static ApplicationContext getContext() {
        return context;
    }
}

