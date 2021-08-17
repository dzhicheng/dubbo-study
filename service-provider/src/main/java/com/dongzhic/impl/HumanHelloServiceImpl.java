package com.dongzhic.impl;

import com.dongzhic.service.HelloService;

/**
 * @Author dongzhic
 * @Date 2021/8/10 07:12
 */
public class HumanHelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        return "hello 你好";
    }
}
