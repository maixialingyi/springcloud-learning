package com.macro.cloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/command")
public class HystrixCommandController {

    /**
     * HystrixProperty的参数可参考 hystrixCommandProperties
     * 熔断触发降级
     */
    @GetMapping("/circuitBreaker/{num}")
    @HystrixCommand(commandProperties  = {
            @HystrixProperty(name = "circuitBreaker.enabled" ,value = "true"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value ="5"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds" , value ="5000"),  //熔断时间5秒
            @HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value = "50")  //错误流程比例

    } ,fallbackMethod = "fallback")
    public String circuitBreaker(@RequestParam int num){
        if(num%2==0){
            return "正常访问";
        }
        throw new RuntimeException("");
    }

    public String fallback(int num){
        return "熔断触发降级";
    }
}
