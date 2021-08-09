package com.dongzhic.bean;

import com.dongzhic.service.HelloService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;

/**
 * @Author dongzhic
 * @Date 2021/8/9 16:26
 */
@Component
public class ConsumerComponent {

    @Reference
    private HelloService helloService;

    public String sayHello (String name) {
        return helloService.sayHello(name);
    }
}
