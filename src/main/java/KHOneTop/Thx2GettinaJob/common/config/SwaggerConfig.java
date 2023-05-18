//package KHOneTop.Thx2GettinaJob.common.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//
//@Configuration
//public class SwaggerConfig {
//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.OAS_30)
//                .useDefaultResponseMessages(false)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("HOneTop.Thx2GettinaJob.auth.controller")
//                        .or(RequestHandlerSelectors.basePackage("HOneTop.Thx2GettinaJob.bookmark.controller"))
//                        .or(RequestHandlerSelectors.basePackage("HOneTop.Thx2GettinaJob.exam.controller"))
//                        .or(RequestHandlerSelectors.basePackage("HOneTop.Thx2GettinaJob.score.controller"))
//                        .or(RequestHandlerSelectors.basePackage("HOneTop.Thx2GettinaJob.user.controller")))
//                .paths(PathSelectors.any())
//                .build()
//                .apiInfo(apiInfo());
//    }
//
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title("KHONETOP Swagger")
//                .description("국힙원탑 API 명세서")
//                .version("1.0")
//                .build();
//    }
//}
