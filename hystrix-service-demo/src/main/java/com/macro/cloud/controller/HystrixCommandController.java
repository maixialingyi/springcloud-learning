package com.macro.cloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/hystrixCommand")
public class HystrixCommandController {

    /**
     * 熔断触发降级  配置项参见 HystrixCommandProperties
     * http://127.0.0.1:8080/hystrixCommand/circuitBreaker?num=2  调用3次,返回成功
     * http://127.0.0.1:8080/hystrixCommand/circuitBreaker?num=1  返回降级内容,调用第3次,后就开启了降级
     * 验证: 调用num=2 返回降级内容  5s 有待研究肯能不是5s 后返回成功
     */
    @GetMapping("/circuitBreaker")
    @HystrixCommand(commandProperties  = {
            @HystrixProperty(name = "circuitBreaker.enabled" ,value = "true"),                    //开启熔断降级功能
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value ="5"),          //最小请求次数，这里是5个请求
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds" , value ="5000"),  //熔断时间5秒
            @HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value = "50")         //错误流程比例

    } ,fallbackMethod = "circuitBreakerFallback")
    public String circuitBreaker(@RequestParam int num){
        if(num%2==0){
            return "正常访问";
        }
        throw new RuntimeException("");
    }

    public String circuitBreakerFallback(int num){
        return "熔断触发降级";
    }


    /**
     * 超时时间触发降级
     * http://127.0.0.1:8080/hystrixCommand/timeOut
     * 备注: 超时后会直接结束主线程执行
     */
    @GetMapping("/timeOut")
    @HystrixCommand(commandProperties  = {
            @HystrixProperty(name = "execution.timeout.enabled" , value = "true"),      //启动超时触发降级
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds" , value = "1000"),//超过1s就触发降级
    } ,fallbackMethod = "timeOutFallback")
    public String timeOut() throws InterruptedException {
        System.out.println("startTime = " + System.currentTimeMillis());
        Thread.sleep(1100) ;
        System.out.println("endTime = " + System.currentTimeMillis());
        return "正常访问";
    }

    public String timeOutFallback(){
        return "触发超时降级";
    }

    /**
     * 信号量降级
     * http://127.0.0.1:8080/hystrixCommand/semaphore  默认超时为1s
     * 备注: 有默认超时 HystrixCommandProperties.default_executionTimeoutInMilliseconds
     * 超时后只是中断了主线程,如果主线程未编写响应中断代码,还会执行主线程,异步调用fallback,都执行完才算完
     */
    @GetMapping("/semaphore")
    @HystrixCommand(
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.strategy" , value = "SEMAPHORE"), //启动信号量隔离
                    @HystrixProperty(name = "execution.isolation.semaphore.maxConcurrentRequests" , value = "1") //信号量大小
            },
            fallbackMethod = "semaphoreFallback"
    )
    public String semaphore() throws InterruptedException {
        System.out.println("startTime = " + System.currentTimeMillis());
        Thread.sleep(900) ;
        System.out.println("endTime = " + System.currentTimeMillis());
        return "semaphore正常访问";
    }

    public String semaphoreFallback(){
        return "触发信号量降级";
    }

    /**
     * 线程池隔离
     * http://127.0.0.1:8080/hystrixCommand/thread
     */
    private int num1 = 1;
    @GetMapping("/thread")
    @HystrixCommand(
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.strategy" , value = "THREAD"),  //使用线程池的隔离
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds" , value = "3000"),  //超时设置为3秒
            },
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize" , value = "1"), //线程池大小
                    @HystrixProperty(name = "maxQueueSize" , value = "10"), //等待队列长度
                    @HystrixProperty(name = "keepAliveTimeMinutes", value = "2"),//线程存活时间
                    @HystrixProperty(name = "queueSizeRejectionThreshold" , value = "1"),//等待队列多少时拒绝
            },
            groupKey = "HystrixCommandController", commandKey = "thread" ,threadPoolKey = "HystrixCommandController",
            fallbackMethod = "threadFallback"
    )
    public void thread() throws  Exception  {
        Thread.sleep(1000);
        System.out.println(Thread.currentThread() + "正常访问" + num1++);
    }

    public void threadFallback(){
        System.out.println("熔断时间：" + new Date());
    }
}
