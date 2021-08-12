package com.dongzhic.service;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

/**
 * 接口继承remote接口, 实现类继承UnicastRemoteObject, 可以实现rmi调用
 */
public interface InfoService extends Remote {
    String RMI_URL = "rmi://127.0.0.1:9080/InfoService";
    int port = 9080;

    Object sayHello(String name) throws RemoteException;
    Object passInfo(Map<String,String> info) throws RemoteException;


}
