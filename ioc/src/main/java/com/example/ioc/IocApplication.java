package com.example.ioc;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication // 이 annotation은 spring-boot로 동작 하게끔 해준다.(자바 객체(bean)을 가지고 있음)
public class IocApplication {

	public static void main(String[] args) {
		SpringApplication.run(IocApplication.class, args);
		ApplicationContext context = ApplicationContextProvider.getContext();

		//DI는 객체를 주입 해주지만, Ioc(객체 관리)는 new로 관리 안함.
		//Bean을 찾을 떈 이름을 가지고 찾거나, 클래스 타입을 가지고도 찾을 수 있음.
		//Base64Encoder base64Encoder = context.getBean(Base64Encoder.class); //bean을 클래스 타입으로 찾음.
		//UrlEncoder urlEncoder = context.getBean(UrlEncoder.class);
		//Encoder encoder = new Encoder(base64Encoder); //DI로 괄호() 안에 base64Encoder 객체 주입


		//Encoder encoder = context.getBean(Encoder.class); 클래스 타입으로 찾을 경우
		Encoder encoder = context.getBean("base64Encode",Encoder.class); //이름으로 찾을 경우
		Encoder encoder1 = context.getBean("urlEncode",Encoder.class);
		String url = "www.naver.com/books/it?page=10&size=20&name=spring-boot";
		String result = encoder.encode(url);
		String result1 = encoder1.encode(url);
		System.out.println(result);
		System.out.println(result1);

/*
		encoder.setIEncoder(urlEncoder);
		result = encoder.encode(url);
		System.out.println(result);
 */
	}
}

@Configuration //한 개의 class에서 여러개의 bean을 등록해서 사용할 경우
class Appconfig{

	@Bean("base64Encode") //Encoder라는 Bean이 두개가 생겼기 때문에 구분해주기 위해 ("")안에 이름을 붙여줌
	public Encoder encoder(Base64Encoder base64Encoder) { //base64Encoder를 spring으로 부터 주입 받음
		return new Encoder(base64Encoder);
	}

	@Bean("urlEncode") //@Component가 urlEncoder라는 이름을 이미 쓰고 있어서 bean을 생성할 때 충돌나기 때문에 이름을 urlEncode로 바꿔줌
	public Encoder encoder(@Qualifier("urlEncoder") UrlEncoder urlEncoder) { //명시적으로 bean의 이름을 사용하고 싶으면 @Qualifier를 사용 가능
		return new Encoder(urlEncoder);
	}
}

