package com.congphuong.reactivechatsse;

import com.congphuong.reactivechatsse.property.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@SpringBootApplication
@ComponentScan(basePackages = {
        "com.congphuong.reactivechatsse.config.security",
        "com.congphuong.reactivechatsse.controller",
        "com.congphuong.reactivechatsse.service",
        "com.congphuong.reactivechatsse.entity",
        "com.congphuong.reactivechatsse.repository",
        "com.congphuong.reactivechatsse.bot"
})
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class ReactiveChatSseApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactiveChatSseApplication.class, args);
    }

//    @Bean
//    public WebFluxConfigurer corsConfigurer() {
//        return new WebFluxConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**").allowedOrigins("http://localhost:3000");
//            }
//        };
//    }
}
