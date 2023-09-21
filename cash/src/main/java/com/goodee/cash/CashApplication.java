package com.goodee.cash;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan // 서블릿, 필터, 리스너를 스캔하여 등록
public class CashApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(CashApplication.class, args);
	}

}
