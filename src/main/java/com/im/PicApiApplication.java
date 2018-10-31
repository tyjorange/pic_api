package com.im;

import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.im.mapper")
public class PicApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PicApiApplication.class, args);
	}
}
