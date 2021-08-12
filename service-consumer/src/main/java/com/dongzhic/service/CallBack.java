package com.dongzhic.service;

/**
 * @Author dongzhic
 * @Date 2021/8/12 17:29
 */
public class CallBack {

    /**
     * @param result 返回结果值
     * @param arg 入参
     */
    public void onSuccess (String result, String arg) {
        System.out.println("调用成功：" + result);
    }

    /**
     * @param ex 返回异常结果值
     * @param arg 入参
     */
    public void onError (Throwable ex, String arg) {
        System.out.println("调用异常，请紧急处理，异常原因：" + ex.getMessage());
    }
}
