package com.congphuong.reactivechatsse;

import com.congphuong.reactivechatsse.property.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class ReactiveChatSseApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactiveChatSseApplication.class, args);
    }
}
