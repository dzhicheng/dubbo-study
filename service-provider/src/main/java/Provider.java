import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * xml方式 提供者
 * @Author dongzhic
 * @Date 2021/8/9 17:30
 */
public class Provider {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:dubbo-provider.xml");
        context.start();
        // 按任意键退出
        System.in.read();
    }
}
