package com.solstice.exchangeservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.solstice.exchangeservice.web"))
                .paths(PathSelectors.any())
                .build()
                .tags(new Tag("Exchange Service Controller", "This is used for getting and posting Exchange Rates"));
    }
}

//@Configuration
//@EnableSwagger2
//public class SwaggerConfig {
//
//    @Bean
//    public Docket productApi() {
//        return new Docket(DocumentationType.SWAGGER_2).select()
//                .apis(RequestHandlerSelectors.basePackage("my.package")).build()
//                .apiInfo(apiInfo())
//                .tags(new Tag("tag1", "Tag 1 description."),
//                        new Tag("tag2", "Tag 2 description."),
//                        new Tag("tag2", "Tag 3 description."));
//    }
//
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder().title("My API").version("1.0.0").build();
//    }
//}
