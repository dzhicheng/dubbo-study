package com.dongzhic.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.dongzhic.entity.OrderEntry;

@Service
public class OrderServiceImpl implements OrderService {

    @Override
    public OrderEntry getDetail(String id) {
        System.out.println(super.getClass().getName()+"被调用一次："+System.currentTimeMillis());
        OrderEntry orderEntiry = new OrderEntry();
        orderEntiry.setId("O0001");
        orderEntiry.setMoney(1000);
        orderEntiry.setUserId("U0001");

        return orderEntiry;
    }

}
