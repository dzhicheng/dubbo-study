package com.dongzhic.service;

import com.dongzhic.entity.OrderEntry;

public interface OrderService {

    /**
     * 目标对象，方法，参数
     * @param id
     * @return
     */
    OrderEntry getDetail(String id);

}
