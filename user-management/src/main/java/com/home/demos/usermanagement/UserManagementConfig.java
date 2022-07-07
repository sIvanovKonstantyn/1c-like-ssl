package com.home.demos.usermanagement;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

@Configuration
public class UserManagementConfig {
    @Bean
    public static BeanFactoryPostProcessor beanFactoryPostProcessor() {
        return new UserBeanFactoryPostProcessor();
    }

    @Bean
    @Scope(scopeName = "user", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public UserContext user() {
        return new UserContext("defaultUser");
    }
}
