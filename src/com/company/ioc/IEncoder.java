package com.company.ioc;

public interface IEncoder {

    String encode(String message); //Encoder, UrlEncoder class에 encode라는 메서드가 같이 쓰이기 때문에 interface하나 만들어서 추상 메서드를 만들어
                                   //각자 클래스에서 재정의 해서 사용
}
