package com.daimler.postsales.automation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class AutomationTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutomationTestApplication.class, args);
    }

}
