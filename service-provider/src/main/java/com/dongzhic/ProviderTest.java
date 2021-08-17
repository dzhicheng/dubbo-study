package com.dongzhic;

import com.alibaba.fastjson.JSON;
import com.dongzhic.entity.OrderEntry;
import com.dongzhic.service.InfoService;
import com.dongzhic.service.OrderService;
import com.dongzhic.impl.InfoServiceImpl;
import com.dongzhic.impl.OrderServiceImpl;
import com.dongzhic.util.InvokeUtils;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.HashMap;
import java.util.Map;

/**
 * xml方式 提供者
 * @Author dongzhic
 * @Date 2021/8/9 17:30
 */
public class ProviderTest {

    @Configuration
    static class ProviderConfiguration {
        @Bean
        public OrderService orderService() {
            return new OrderServiceImpl();
        }
    }

    /**
     * rpc调用方式
     *  不同方式调用接口
     * @throws IOException
     * @throws AlreadyBoundException
     */
    @Test
    public void callApi () {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ProviderConfiguration.class);
        ctx.start();
        System.out.println("=======================spring启动成功=======================");

        System.out.println("-------------方式一：通过application.getBean()调用---------------");
        OrderService orderService = (OrderService) ctx.getBean("orderService");
        OrderEntry entry = orderService.getDetail("1");
        System.out.println("测试orderService.getDetail调用功能，调用结果：" + JSON.toJSONString(entry));


        Map<String,String> info = new HashMap();
        info.put("target","orderService");
        info.put("methodName","getDetail");
        info.put("arg","1");
        Object result = InvokeUtils.call(info,ctx);
        System.out.println("测试InvokeUtils.call调用功能，调用结果：" + JSON.toJSONString(result));

    }

    /**
     * 通过通过xml方式启动提供者
     * @throws IOException
     */
    @Test
    public void startProviderOfXml () throws IOException {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:dubbo-provider.xml");
        context.start();

        System.out.println("=======================dubbo容器通过xml方式启动=======================");
        // 按任意键退出
        System.in.read();
    }

    /**
     * 启动提供者：rmi协议
     * @throws IOException
     * @throws AlreadyBoundException
     */
    @Test
    public void startProviderOfRmi () throws IOException, AlreadyBoundException {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ProviderConfiguration.class);
        ctx.start();

//        initProtocol(ctx);
        initProtocol2(ctx);
        System.in.read();
    }


    /**
     * rmi服务暴露
     * @param ctx
     * @throws RemoteException
     * @throws AlreadyBoundException
     * @throws MalformedURLException
     */
    public static void initProtocol(ApplicationContext ctx) throws RemoteException, AlreadyBoundException, MalformedURLException {
        InfoService infoService = new InfoServiceImpl();
        //注冊通讯端口
        LocateRegistry.createRegistry(InfoService.port);
        //注冊通讯路径
        Naming.bind(InfoService.RMI_URL, infoService);
        System.out.println("初始化rmi绑定");
    }

    public static void initProtocol2(ApplicationContext ctx) throws RemoteException, AlreadyBoundException, MalformedURLException {
        InfoService infoService = new InfoServiceImpl(){
            //对象，方法，参数
            public Object passInfo(Map<String, String> info) {
                //info内包含的信息，是反射需要的信息
                super.passInfo(info);
                Object result = InvokeUtils.call(info,ctx);
                System.out.println("测试InvokeUtils.call调用功能，调用结果：" + JSON.toJSONString(result));
                return result;
            }
        };

        //注冊通讯端口
        LocateRegistry.createRegistry(InfoService.port);
        //注冊通讯路径
        Naming.bind(InfoService.RMI_URL, infoService);
        System.out.println("初始化rmi绑定");
    }

}
