package com.fu.prenancytracker.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfig {

    // Lấy giá trị URL của môi trường phát triển từ tệp cấu hình
    @Value("${pregnancy.openapi.dev-url}")
    private String devUrl;

    // Lấy giá trị URL của môi trường sản xuất từ tệp cấu hình
    @Value("${pregnancy.openapi.prod-url}")
    private String prodUrl;

    // Định nghĩa một Bean OpenAPI để cấu hình tài liệu OpenAPI (Swagger)
    @Bean
    public OpenAPI myOpenAPI() {
        // Cấu hình Server cho môi trường phát triển
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Server URL in Development environment");

        // Cấu hình Server cho môi trường sản xuất
        Server prodServer = new Server();
        prodServer.setUrl(prodUrl);
        prodServer.setDescription("Server URL in Production environment");

        // Cấu hình thông tin giấy phép sử dụng API
        License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

        // Cấu hình thông tin API
        Info info = new Info()
                .title("BMI Tracker API") // Tiêu đề API
                .version("1.0") // Phiên bản API
                .description("This API exposes endpoints to BMI Tracker system.") // Mô tả API
                .license(mitLicense); // Thêm thông tin giấy phép

        // Cấu hình yêu cầu bảo mật cho API với JWT
        SecurityRequirement securityRequirement = new SecurityRequirement()
                .addList("Bearer Authentication");

        // Định nghĩa các scheme bảo mật trong API
        Components components = new Components()
                .addSecuritySchemes("Bearer Authentication", createAPIKeyScheme());

        // Trả về cấu hình OpenAPI hoàn chỉnh
        return new OpenAPI()
                .addSecurityItem(securityRequirement)
                .components(components)
                .info(info)
                .servers(List.of(devServer, prodServer));
    }

    // Phương thức tạo SecurityScheme sử dụng JWT Bearer Token
    private SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme()
                .type(SecurityScheme.Type.HTTP) // Kiểu xác thực HTTP
                .bearerFormat("JWT") // Định dạng token là JWT
                .scheme("bearer"); // Cơ chế Bearer Token
    }
}
