package com.bookstore.shop;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopApplication.class, args);
	}

	//주문조회 v1 프록시에러 해결
	@Bean
	Hibernate5Module hibernate5Module(){
		return new Hibernate5Module();
	}
}
