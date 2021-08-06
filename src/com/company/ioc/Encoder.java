package com.company.ioc;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Encoder {

    private IEncoder iEncoder; //IEncoder를 내부적으로 선언

    /*
    public Encoder(){ //밑에 base64Encoder와 UrlEncoder의 객체를 번갈아 사용하면서 코드의 본질을 건드리면서 encoding을 하고 있음
        //this.iEncoder = new Base64Encoder(); //Base64Encoder 객체를 사용
        this.iEncoder = new UrlEncoder();
    }
    */

    public Encoder(IEncoder iEncoder) { //생성자에서 interface를 받음
        this.iEncoder = iEncoder;
    }

    public String encode(String message) {
        //return Base64.getEncoder().encodeToString(message.getBytes());
        return iEncoder.encode(message);
    }
}
