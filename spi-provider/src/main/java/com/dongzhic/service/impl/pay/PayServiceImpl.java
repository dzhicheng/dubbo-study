package com.dongzhic.service.impl.pay;

import com.dongzhic.service.PayService;
import org.apache.dubbo.config.annotation.Service;

@Service
public class PayServiceImpl implements PayService {
    @Override
    public String pay(long money) {

        System.out.println("PayService.pay耗时2秒");
        return String.valueOf(money);
    }
}
