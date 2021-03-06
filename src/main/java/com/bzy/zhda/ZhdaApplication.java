package com.bzy.zhda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Auther: lkw
 * @Date: 2018/6/25 17:00
 * @Description: INIT CLASS
 */
@SpringBootApplication
@EnableSwagger2
public class ZhdaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZhdaApplication.class, args);
	}
}
