package com.dongzhic.controller;

import com.alibaba.dubbo.rpc.RpcContext;
import com.dongzhic.entity.OrderEntry;
import com.dongzhic.entity.ProductEntry;
import com.dongzhic.entity.UserEntry;
import com.dongzhic.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;


@Controller
public class IndexController {

    @Autowired
    private ProductService productService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;
    @Autowired
    private PayService payService;
    @Autowired
    private ErrorService errorService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        ProductEntry productEntiry = productService.getDetail("1");
        OrderEntry orderView = orderService.getDetail(id);

        request.setAttribute("productView", productEntiry);
        request.setAttribute("orderView", orderView);
        return "index";
    }

    /**
     * 异步并发调用
     */
    @RequestMapping(value = "/async", method = RequestMethod.GET)
    public String cancel(HttpServletRequest request, HttpServletResponse response)  {
        long start = System.currentTimeMillis();

        //若设置了async=true，方法立即返回null
        UserEntry user = userService.getDetail("1");
        //只有async=true，才能得到此对象，否则为null
        Future<UserEntry> userEntryFuture = RpcContext.getContext().getFuture();//threadLocal绑定的

        String pay = payService.pay(100);
        Future<String> payFuture = RpcContext.getContext().getFuture();

        //Future取得返回值，此处阻塞
        try {
            user = userEntryFuture.get();//阻塞的
            pay = payFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        request.setAttribute("user", user);
        request.setAttribute("pay", pay);

        long time = System.currentTimeMillis() - start;
        request.setAttribute("time", time);

        return "/async";
    }

    /**
     * 事件通知
     */
    @RequestMapping(value = "/error", method = RequestMethod.GET)
    @ResponseBody
    public String error(HttpServletRequest request, HttpServletResponse response){
        String msg = errorService.doSomeThing("测试业务稳定性");
        return "complete:"+msg;
    }

}
