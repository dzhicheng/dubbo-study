package com.dongzhic.service.impl.filter;

import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;

/**
 * 使用方传递了group = Constants.PROVIDER 或者Constants.CONSUMER则该Filter激活
 */
@Activate(group = {"provider", "consumer"})
public class FilterA implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        System.out.println("你好，调通了Filer A实现！");
        return null;
    }
}
