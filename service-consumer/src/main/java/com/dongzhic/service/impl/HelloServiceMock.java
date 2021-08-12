package com.dongzhic.service;

/**
 * 服务降级
 * @Author dongzhic
 * @Date 2021/8/9 21:01
 */
public class HelloServiceMock implements HelloService{

    @Override
    public String sayHello(String name) {
        return "hello mock";
    }
}
