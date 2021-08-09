import com.dongzhic.service.HelloService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * xml方式 消费者
 * @Author dongzhic
 * @Date 2021/8/9 17:34
 */
public class Consumer {

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:dubbo-consumer.xml");
        context.start();
        // 获取远程服务代理
        HelloService helloService = (HelloService)context.getBean("helloService");
        // 执行远程方法
        String hello = helloService.sayHello("world");
        // 显示调用结果
        System.out.println( hello );
    }

}
