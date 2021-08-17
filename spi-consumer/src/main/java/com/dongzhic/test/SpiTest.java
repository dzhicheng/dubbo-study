package com.dongzhic.test;

import com.dongzhic.service.InfoService;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.junit.Test;

import java.util.ServiceLoader;

/**
 * @Author dongzhic
 * @Date 2021/8/12 23:22
 */
public class SpiTest {

    /**
     * java 编码引入实现，显示引入InfoServiceAImpl
     * 若实现类有了改动，比如切换另一种实现，使用者必须重新编码
     */
    @Test
    public void javaSPI () {
        // 服务加载器，加载实现类，获取META-INF/services下面的文件
        ServiceLoader<InfoService> serviceLoader = ServiceLoader.load(InfoService.class);

        // serviceLoader是实现Iterable的迭代器，直接遍历实现类
        for (InfoService service : serviceLoader) {
            // 一次调用实现类
            Object result = service.sayHello("dongzhic");
        }
    }

    /**
     * dubbo spi类加载验证
     */
    @Test
    public void dubboSPI () {

        // 获取InfoService的Loader实例
        ExtensionLoader<InfoService> extensionLoader = ExtensionLoader.getExtensionLoader(InfoService.class);

        // 取得a拓展类
        InfoService infoServiceA = extensionLoader.getExtension("a");
        infoServiceA.sayHello("你好，a拓展类");

        // 取得b拓展类，@SPI("b")
        InfoService infoServiceB = extensionLoader.getDefaultExtension();
        infoServiceB.sayHello("你好，默认拓展类");
    }

}
