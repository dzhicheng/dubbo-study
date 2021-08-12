package com.dongzhic.service.impl;


import com.dongzhic.entity.OrderEntry;
import com.dongzhic.service.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Override
    public OrderEntry getDetail(String s) {

        System.out.println(super.getClass().getName()+"被调用一次：" + System.currentTimeMillis());
        OrderEntry orderEntry = new OrderEntry();
        orderEntry.setId("O0001");
        orderEntry.setMoney(1000);
        orderEntry.setUserId("U0001");

        return orderEntry;
    }
}
