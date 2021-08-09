package com.example.ioc;

import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Component //component의 이름은 클래스에 아무런 명칭없이 생성을 하면 제일 앞글자만 소문자로 변한다. 아니면 ("encoder") 이렇게 괄호 안에 맘대로 명칭 가능
public class UrlEncoder implements IEncoder {

    public String encode(String message) {

        try {
            return URLEncoder.encode(message, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
