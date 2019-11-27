package com.heystyles.producto.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {
        "com.heystyles.producto.api.async",
        "com.heystyles.producto.api.config",
        "com.heystyles.producto.api.controller",
        "com.heystyles.producto.api.converter",
        "com.heystyles.producto.api.dao",
        "com.heystyles.producto.api.entity",
        "com.heystyles.producto.api.service",
        "com.heystyles.producto.api.exception",
        "com.heystyles.producto.api.http",
        "com.heystyles.producto.api.message",
        "com.heystyles.producto.api.service",
        "com.heystyles.producto.api.validator",
})
@SpringBootApplication
public class ProductoApp extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ProductoApp.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(ProductoApp.class, args);
    }

}
