package com.dongzhic.util;

import com.dongzhic.service.InfoService;
import com.dongzhic.service.impl.InfoServiceImpl;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class RmiServer {
    public static void main(String[] args) {
        try {
            InfoService infoService = new InfoServiceImpl();

            // rmi服务端的绑定
            // 1.注冊通讯端口
            LocateRegistry.createRegistry(InfoService.port);
            // 2.注冊通讯路径
            Naming.bind(InfoService.RMI_URL, infoService);
            System.out.println("初始化rmi绑定");

        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }
    }
}
