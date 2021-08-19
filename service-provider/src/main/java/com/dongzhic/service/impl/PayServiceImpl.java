package com.dongzhic.service.impl;

import com.dongzhic.service.PayService;

import java.util.concurrent.TimeUnit;

/**
 * @Author dongzhic
 * @Date 2021/8/12 16:32
 */
public class PayServiceImpl implements PayService {
    @Override
    public String pay(long money) {
        try {
            System.out.println("PayServiceImpl.pay耗时2秒");
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return String.valueOf(money);
    }
}
