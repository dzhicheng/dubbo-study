package com.dongzhic.filter;

import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;

@Activate(group = {"consumer"})
public class EnjoyFilter implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        System.out.println("添加了url参数");
        return invoker.invoke(invocation);
    }
}
