package com.example.springbootdemo.controller;

import com.example.springbootdemo.beans.DeferredResultQueue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

@RestController
public class TestAsyncController {
    /**
     * 1、控制器返回Callable
     * 2、Spring异步处理，将Callable提交到TaskExecutor，使用一个隔离的线程执行
     * 3、DispatcherServlet and all Filter’s exit the Servlet container thread but the response remains open。
     * 4、Callable 返回结果，SpringMVC 将请求重新派发给容器，恢复之前的处理。
     * 5、根据Callable返回的结果。SpringMVC继续进行视图的渲染流程等（从收请求-视图渲染）。
     * <p>
     * 主线程开始...http-nio-22000-exec-1==>1550454364058
     * 主线程结束...http-nio-22000-exec-1==>1550454364058
     * 2019-02-18 09:46:04.124  WARN 7300 --- [io-22000-exec-1] o.s.w.c.request.async.WebAsyncManager    :
     * !!!
     * An Executor is required to handle java.util.concurrent.Callable return values.
     * Please, configure a TaskExecutor in the MVC config under "async support".
     * The SimpleAsyncTaskExecutor currently in use is not suitable under load.
     * -------------------------------
     * Request URI: '/async01'
     * !!!
     * 副线程开始...MvcAsync1==>1550454364167
     * 副线程开始...MvcAsync1==>1550454366167
     *
     * @return
     */
    @RequestMapping("/async01")
    public Callable<String> async01() {
        System.out.println("主线程开始..." + Thread.currentThread().getName() + "==>" + System.currentTimeMillis());
        Callable<String> callable = () -> {
            System.out.println("副线程开始..." + Thread.currentThread().getName() + "==>" + System.currentTimeMillis());
            TimeUnit.SECONDS.sleep(2);
            System.out.println("副线程结束..." + Thread.currentThread().getName() + "==>" + System.currentTimeMillis());
            return "hello world";
        };
        System.out.println("主线程结束..." + Thread.currentThread().getName() + "==>" + System.currentTimeMillis());

        return callable;
    }


    @RequestMapping("/createOrder")
    public DeferredResult<Object> createOrder() {

        DeferredResult<Object> result = new DeferredResult<>((long) 3000, "create fail......");

        DeferredResultQueue.save(result);

        return result;
    }

    @RequestMapping("/create")
    public String create() {
        //创建订单
        String order = UUID.randomUUID().toString();
        DeferredResult<Object> result = DeferredResultQueue.get();
        System.out.println(result);
        if (result != null) {
            result.setResult(order);
        }

        return "success======>" + order;
    }

}
