package com.bzy.zhdn.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


/**
 * @Auther: lkw
 * @Date: 2018/6/25 17:00
 * @Description: Swagger2Config
 */
@Configuration
public class Swagger2Config {


    /**
     * @Desc: SWAGGER2配置
     * @Param: ApiInfo
     * @Return: Docket
     * @Auther: lkw
     * @Date: 2018/6/25 21:31
     */
    @Bean
    public Docket createRestApi(ApiInfo apiInfo) {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build();
        }

       
    /**
     * @remark: lkw created by time: 2018/6/25 21:36
     */
    @Bean
    public ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
                .title("ZHDN")
                .description("ZHDN_API")
                .version("1.0")
                .build();
    }


}
