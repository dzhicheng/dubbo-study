package com.dongzhic.service.impl;

import com.dongzhic.service.HelloService;
import org.apache.dubbo.config.annotation.Service;

/**
 * 注入spring容器
 * @Author dongzhic
 * @Date 2021/8/9 12:51
 */
@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
//        try {
//            TimeUnit.SECONDS.sleep(3);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return "hello:" + name;
    }
}
