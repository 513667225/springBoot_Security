package com.bzy.zhdn;

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
public class ZhdnApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZhdnApplication.class, args);
	}
}
