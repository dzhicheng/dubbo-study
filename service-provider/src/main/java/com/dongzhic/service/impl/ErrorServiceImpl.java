package com.dongzhic.service.impl;

import com.dongzhic.service.ErrorService;

/**
 * @Author dongzhic
 * @Date 2021/8/12 17:33
 */
public class ErrorServiceImpl implements ErrorService {

    @Override
    public String doSomeThing(String arg) {
        if (System.currentTimeMillis()%3 == 0) {
            throw new RuntimeException("sorry, something error !");
        }
        return "success";
    }
}
