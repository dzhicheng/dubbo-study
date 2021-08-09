package com.dongzhic;

import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.concurrent.TimeUnit;

/**
 * 完成服务注册
 */
public class DubboPureMain {
    public static void main( String[] args ) throws InterruptedException {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ProviderConfiguration.class);
        applicationContext.start();
        TimeUnit.SECONDS.sleep(60);
    }

    /**
     * 服务提供者配置类
     */
    @Configuration
    @EnableDubbo(scanBasePackages = "com.dongzhic.service.impl")
    @PropertySource("classpath:/dubbo-provider.properties")
    static class ProviderConfiguration {
        // 加载注册中心配置
        @Bean
        public RegistryConfig registryConfig () {
            RegistryConfig registryConfig = new RegistryConfig();
            registryConfig.setAddress("zookeeper://127.0.0.1:2181");
            return registryConfig;
        }
    }

}
