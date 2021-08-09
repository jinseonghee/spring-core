package com.example.ioc;

import org.springframework.stereotype.Component;

import java.util.Base64;

@Component  //이 클래스는 spring에서 관리를 해달라는 의미(클래스를 bean으로 만들어서 관리하라는 뜻)
            //spring이 실행이 될 때 이 @annotation이 붙은 클래스를 찾아서 직접 객체를 싱글톤 형태로 만들어서 spring container에서 관리
            //이걸 꺼내서 쓸 땐, spring application context라는 걸 통해서 객체를 가져옴
public class Base64Encoder implements IEncoder {

    public String encode(String message) {
        return Base64.getEncoder().encodeToString(message.getBytes());
    }
}
