package com.dongzhic;

import org.apache.dubbo.rpc.service.EchoService;
import org.apache.dubbo.rpc.service.GenericService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class DubboTest {

    /**
     * 回声测试：扫一遍服务是否都已就绪
     */
    @Test
    public void echoTest() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:dubbo/dubbo-Test.xml");

        ctx.start();
        System.out.println("---------dubbo启动成功--------");

        String[] serviceIds = new String[]{"errorService","userService","orderService","payService"};

        Object ret = null;
        for (String id:serviceIds){
            try {//reference代理对象，强制转换为EchoService
                EchoService echoService = (EchoService)ctx.getBean(id);
                ret = echoService.$echo("ok");
            } catch (Exception e) {
                ret = "not ready";
            }
            System.out.println("service:"+id+":"+ret);
        }
    }

    /**
     * 泛化调用
     * 当前项目，没有对应的接口---- com.enjoy.service.OtherService
     */
    @Test
    public void other() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:dubbo/dubbo-Test.xml");

        ctx.start();
        System.out.println("---------dubbo启动成功--------");

        // 类为unknownService，方法为doSomething
        GenericService genericService = (GenericService)ctx.getBean("unknownService");

        Object ret = genericService.$invoke("doSomething",new String[]{"java.lang.String"},new Object[]{"peter"});
        System.out.println("调用结果："+ret.toString());
    }

}
