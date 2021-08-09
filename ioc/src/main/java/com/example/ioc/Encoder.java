package com.example.ioc;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


public class Encoder {

    private IEncoder iEncoder;


    /* 객체를 @component로 등록했을 경우
    public Encoder(@Qualifier("base64Encoder") IEncoder iEncoder) { //bean이 하나만 있으면 매칭을 시켜 주지만, 여기선 bean으로 등록된게 두 개 이기 때문에 spring이 결정을 못해 생기는 error
        this.iEncoder = iEncoder; // 이럴 경우 @Qualifier를 통해 어떤 bean을 사용할지 지정
    }
     */

    public Encoder(IEncoder iEncoder) {
        this.iEncoder = iEncoder;
    }

    //Spring에서 bean 을 주입받을 수 있는 장소 : 변수, 생성자, setMethod()
    public void setIEncoder(IEncoder iEncoder) {
        this.iEncoder = iEncoder;
    }

    public String encode(String message) {
        return iEncoder.encode(message);
    }
}
