package com.example.study1.component.swagger

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.Operation
import io.swagger.v3.oas.models.PathItem
import io.swagger.v3.oas.models.responses.ApiResponse
import org.springdoc.core.customizers.OpenApiCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import java.util.function.Consumer


@Configuration
class SwaggerConfig {
    @Bean
    fun swaggerApi(): Docket =
        Docket(DocumentationType.OAS_30)
            .consumes(getConsumeContentTypes())
            .produces(getProduceContentTypes())
            .apiInfo(swaggerInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.example.study1.controller"))
            .paths(PathSelectors.any())
            .build()
            .useDefaultResponseMessages(false)

    @Bean
    fun openApiCustomizer(): OpenApiCustomizer {
        return OpenApiCustomizer { openApi: OpenAPI ->
            openApi
                .paths
                .values
                .forEach(
                    Consumer<PathItem> { pathItem: PathItem ->
                        pathItem.readOperations()
                            .forEach(
                                Consumer<Operation> { operation: Operation ->
                                    operation
                                        .responses
                                        .addApiResponse(
                                            "200",
                                            ApiResponse().description("조회 성공")
                                        )
                                        .addApiResponse(
                                            "400",
                                            ApiResponse().description("잘못된 요청")
                                        )
                                        .addApiResponse(
                                            "404",
                                            ApiResponse().description("유효하지않은 API 주소")
                                        )
                                        .addApiResponse(
                                            "500",
                                            ApiResponse().description("서버 오류 - 관리자에게 문의하세요.")
                                        )
                                })
                    })
        }
    }

    private fun swaggerInfo() = ApiInfoBuilder()
        .title("스웨거 테스트")
        .description("스웨거로 API를 테스트")
        .version("1.0.0")
        .build()

    private fun getConsumeContentTypes(): Set<String> {
        val consumes = HashSet<String>()
        consumes.add("multipart/form-data")
        return consumes
    }

    private fun getProduceContentTypes(): Set<String> {
        val produces = HashSet<String>()
        produces.add("application/json;charset=UTF-8")
        return produces
    }
}