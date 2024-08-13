package com.movie.rating.infrastructure.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
public class H2ServerBeanPostProcessor implements BeanPostProcessor {

    private final H2ServerConfig h2ServerConfig;

    public H2ServerBeanPostProcessor(H2ServerConfig h2ServerConfig) {
        this.h2ServerConfig = h2ServerConfig;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals("entityManagerFactory")) {
            try {
                h2ServerConfig.startH2Server();
            } catch (SQLException e) {
                throw new BeansException("Failed to start H2 server", e) {};
            }
        }
        return bean;
    }
}