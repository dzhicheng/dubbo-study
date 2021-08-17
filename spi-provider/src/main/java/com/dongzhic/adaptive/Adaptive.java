package com.dongzhic.adaptive;

import com.dongzhic.service.InfoService;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.ExtensionLoader;

/**
 * Created by Peter on 1/19 019.
 */
public class Adaptive {
}

class InfoService$Adaptive2 implements InfoService {

    public Object passInfo(String arg0, URL arg1) {
        if (arg1 == null) {
            throw new IllegalArgumentException("url == null");
        }
        URL url = arg1;
        String extName = url.getParameter("mark", "b");

        if(extName == null) {
            throw new IllegalStateException("Fail to get extension(InfoService) name from url(" + url.toString() + ") use keys([mark])");
        }

        InfoService extension = ExtensionLoader.getExtensionLoader(InfoService.class).getExtension(extName);
        return extension.passInfo(arg0, arg1);
    }
    public Object sayHello(String arg0) {
        throw new UnsupportedOperationException("method public abstract java.lang.Object InfoService.sayHello(java.lang.String) of interface InfoService is not adaptive method!");
    }
}