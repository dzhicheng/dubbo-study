package com.dongzhic.cluster;

import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.RpcException;
import org.apache.dubbo.rpc.cluster.Cluster;
import org.apache.dubbo.rpc.cluster.Directory;
import org.apache.dubbo.rpc.cluster.support.FailfastClusterInvoker;

public class FailSmsCluster implements Cluster {

    @Override
    public <T> Invoker<T> join(Directory<T> directory) throws RpcException {
        sendSms();
        return new FailfastClusterInvoker<>(directory);
    }

    private void sendSms(){
        System.out.println("send sms notify！");
    }
}
