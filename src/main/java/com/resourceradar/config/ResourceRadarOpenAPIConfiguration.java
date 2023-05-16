package com.resourceradar.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@OpenAPIDefinition(
        info =
        @Info(
                title = "Resource Radar API",
                description = "This API offers a wide range of project management features, including the ability to add employees, create new projects, and allocate resources",
                version = "1.0",
                contact = @Contact(name = "Fission Labs", email = "info@fissionlabs.com")))
public class ResourceRadarOpenAPIConfiguration {
}
