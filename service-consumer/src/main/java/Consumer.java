import com.alibaba.fastjson.JSON;
import com.dongzhic.service.InfoService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

public class Consumer {
    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ConsumerConfiguration.class);
        ctx.start();

        System.out.println("---------spring启动成功--------");

//        OrderService orderService = (OrderService) ctx.getBean("orderService");
//        OrderEntiry entiry = orderService.getDetail("1");
//        System.out.println("测试orderService.getDetail调用功能，调用结果：" + JSON.toJSONString(entiry));

        InfoService infoService = (InfoService) ctx.getBean("infoService");
//        Object o = infoService.sayHello("james");
//        System.out.println("测试infoService.getInfo调用功能，调用结果：" + JSON.toJSONString(o));
//
        Map<String,String> info = new HashMap(16);
        info.put("target","orderService");
        info.put("methodName","getDetail");
        info.put("arg","1");
        Object result = infoService.passInfo(info);
        System.out.println("测试远程调用功能，调用结果：" + JSON.toJSONString(result));

//        System.in.read();
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
