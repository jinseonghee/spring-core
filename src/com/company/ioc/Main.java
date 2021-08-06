package com.company.ioc;

public class Main {

    public static void main(String[] args) {
        String url = "www.naver.com/books/it?paage=10&size=20&name=spring-boot";

        // Base 64 encoding
        //Encoder encoder = new Encoder();
        //IEncoder encoder = new Base64Encoder(); //Interface 생성후 implements
        //Encoder encoder = new Encoder(); //Encoder 클래스에 IEnoder 인터페이스를 내부적으로 선언
        //String result = encoder.encode(url);

        // url encoding
        //UrlEncoder urlEncoder = new UrlEncoder();
        //IEncoder urlEncoder = new UrlEncoder(); //Interface 생성후 implements
        //String urlResult = urlEncoder.encode(url);

        //Encoder encoder = new Encoder(); //Encoder 클래스에 IEncoder 인터페이스를 내부적으로 선언

        Encoder encoder = new Encoder(new Base64Encoder()); //필요한 객체를 ()에 주입(DI)하면 위에처럼 Encoder를 건들지 않아도 동작
        String result = encoder.encode(url);
        System.out.println(result);

        Encoder encoder1 = new Encoder(new UrlEncoder());
        String result1 = encoder1.encode(url);
        System.out.println(result1);
    }
}
