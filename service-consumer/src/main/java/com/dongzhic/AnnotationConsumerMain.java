package com.dongzhic;

import com.dongzhic.bean.ConsumerComponent;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @Author dongzhic
 * @Date 2021/8/9 16:35
 */
public class AnnotationConsumerMain {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ConsumerConfiguration.class);
        applicationContext.start();
        // 获取消费者组件
        ConsumerComponent component = (ConsumerComponent) applicationContext.getBean("consumerComponent");
        String result = component.sayHello("张三");
        System.out.println(result);
    }

    @Configuration
    @PropertySource("classpath:/dubbo-consumer.properties")
    @ComponentScan(basePackages = "com.dongzhic.bean")
    @EnableDubbo
    static class ConsumerConfiguration {

    }
}
