package com.daimler.postsales.automation.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class CommonConfig {
    @Value("${app.login.token}")
    private String loginToken;
}
