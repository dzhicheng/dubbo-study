package com.dongzhic.dubbo.xml;

import com.dongzhic.entity.OrderEntry;
import com.dongzhic.service.OrderService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XmlClient {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:dubbo/dubbo-client.xml");

        ctx.start();
        System.out.println("---------dubbo启动成功--------");

        OrderService orderService = (OrderService) ctx.getBean("orderService"); // get remote service proxy

        OrderEntry entiry = orderService.getDetail("1");
        System.out.println("echo result: " + entiry.getMoney());

    }
}
