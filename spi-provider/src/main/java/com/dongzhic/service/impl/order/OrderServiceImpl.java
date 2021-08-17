package com.dongzhic.service.impl.order;

import com.dongzhic.service.InfoService;
import com.dongzhic.service.OrderService;
import org.apache.dubbo.common.URL;

public class OrderServiceImpl implements OrderService {

    /**
     * dubbo的扩展点，是spring的bean接口
     */
    private InfoService infoService;

    public void setInfoService(InfoService infoService) {
        this.infoService = infoService;
    }

    @Override
    public String getDetail(String name, URL url) {
        infoService.passInfo(name,url);
        System.out.println(name+",OrderServiceImpl订单处理成功！");
        return name + ",你好，OrderServiceImpl订单处理成功！";
    }
}
