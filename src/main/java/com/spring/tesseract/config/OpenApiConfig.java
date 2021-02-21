package com.spring.tesseract.config;

import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.Parameter;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.method.HandlerMethod;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Configuration
public class OpenApiConfig implements OperationCustomizer {

    final Environment environment;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(metaData());
    }

    private Info metaData() {
        return new Info()
                .title(environment.getProperty("openapi.title"))
                .description(environment.getProperty("openapi.description"))
                .version(environment.getProperty("openapi.version"))
                .termsOfService(environment.getProperty("openapi.terms-of-service-url"))
                .license(new License().
                        name(environment.getProperty("openapi.license"))
                        .url(environment.getProperty("openapi.license-url"))
                );
    }

    @Override
    public Operation customize(Operation operation, HandlerMethod handlerMethod) {
        Parameter parameterHeader = new Parameter()
                .in(ParameterIn.QUERY.toString())
                .description("Enter media type: xml,yaml or json. (Default: json)")
                .required(true)
                .name("mediaType")
                .example("json")
                .allowEmptyValue(false)
                .schema(new StringSchema()
                        .addEnumItem("json")
                        .addEnumItem("xml")
                        .addEnumItem("yaml")
                        ._default("json"));

        operation.addParametersItem(parameterHeader);
        return operation;
    }
}

