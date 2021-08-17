package com.dongzhic.impl;

import com.dongzhic.entity.UserEntry;
import com.dongzhic.service.UserService;

import java.util.concurrent.TimeUnit;

/**
 * @Author dongzhic
 * @Date 2021/8/10 09:48
 */
public class UserServiceImpl implements UserService {

    @Override
    public UserEntry getDetail(String id) {
        try {
            System.out.println("UserServiceImpl.getDetail耗时2秒");
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        UserEntry user = new UserEntry();
        user.setId("U001");
        user.setName("李四");
        user.setAddress("广州");
        user.setBalance(5000);

        return user;
    }

}
