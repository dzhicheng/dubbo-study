package com.dongzhic.service;

import com.dongzhic.entity.OrderEntiry;

public interface OrderService {
    // 调方法时，知道目标对象，方法，参数
    OrderEntiry getDetail(String id);
}
