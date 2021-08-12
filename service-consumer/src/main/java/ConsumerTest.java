import com.alibaba.fastjson.JSON;
import com.dongzhic.entity.OrderEntry;
import com.dongzhic.service.InfoService;
import com.dongzhic.service.OrderService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

public class ConsumerTest {

    /**
     * 启动消费者：rmi协议
     */
    @Test
    public void startConsumerOfRmi () throws IOException {

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ConsumerConfiguration.class);
        ctx.start();
        System.out.println("=======================spring启动成功=======================");

        System.out.println("-------------方式一：通过application.getBean()调用---------------");
        OrderService orderService = (OrderService) ctx.getBean("orderService");
        OrderEntry entry = orderService.getDetail("1");
        System.out.println("测试orderService.getDetail调用功能，调用结果：" + JSON.toJSONString(entry));

//        System.out.println("-------------方式二：通过反射方式调用---------------");
//        InfoService infoService = (InfoService) ctx.getBean("infoService");
//        Object o = infoService.sayHello("james");
//        System.out.println("测试infoService.getInfo调用功能，调用结果：" + JSON.toJSONString(o));
//
//        Map<String,String> info = new HashMap(16);
//        info.put("target","orderService");
//        info.put("methodName","getDetail");
//        info.put("arg","1");
//        Object result = infoService.passInfo(info);
//        System.out.println("测试远程调用功能，调用结果：" + JSON.toJSONString(result));

        System.in.read();
    }

    @Configuration
    static class ConsumerConfiguration {

        @Bean
        public InfoService infoService() throws RemoteException, MalformedURLException, AlreadyBoundException {
            InfoService infoService = null;

            try {
                //取远程服务实现
                infoService = (InfoService) Naming.lookup(InfoService.RMI_URL);
            } catch (NotBoundException e) {
                e.printStackTrace();
            }

            return infoService;
        }
    }

}
